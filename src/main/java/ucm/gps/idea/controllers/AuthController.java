package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Role;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.models.RegisterUser;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.RoleService;
import ucm.gps.idea.services.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CreatorService creatorService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody RegisterUser regUser) {
        // NOTA: De front nos llega "Creador" o "Empresa"
        
        User user = new User();
        user.setUsername(regUser.getUsername());
        user.setPassword(encoder.encode(regUser.getPassword()));
        user.setType(regUser.getType());
        user.setActive(true);
        user.setEmail(regUser.getEmail());
        user.setName(regUser.getName());
        user.setAddress(regUser.getAddress());
        user.setTelephone(regUser.getTelephone());
        
        user = userService.save(user);

        switch(user.getType()){
            case "Creador":
                Role role = new Role();
                role.setUserId(user.getId());
                role.setName("ROLE_CREATOR");
                System.out.print(role.toString());
                roleService.save(role);

                Creator creator = new Creator();

                break;
            case "Empresa":
                break;
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    
        /*
        if(user.getType().equalsIgnoreCase("Creador")){
           Creator c = new Creator(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                    regUser.getType(), true, regUser.getEmail(), regUser.getName(),
                    regUser.getLastName(), regUser.getBirtDate(), regUser.getTelephone(), regUser.getAddress());
            Creator c = new Creator();
            c.setLastName(regUser.getLastName());
            try {
                logger.info(regUser.getBirthDate());
                c.setBirthDate(new SimpleDateFormat("yy-mm-dd").parse(regUser.getBirthDate()));
            }catch (Exception e){
                logger.info("entro al catch");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            c = creatorService.create(c);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        else if(user.getType().equalsIgnoreCase("Empresa")){
            Enterprise e = new Enterprise(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                    regUser.getType(), true, regUser.getEmail(), regUser.getName(),
                    regUser.getCif(), regUser.getAddress(), regUser.getTelephone(), regUser.getCreditCard(),
                    regUser.getRemainingIdeas());
            Enterprise e = new Enterprise();
            e.setCIF(regUser.getCif());
            e.setCreditCard(e.getCreditCard());
            e.setRemainingIdeas(e.getRemainingIdeas());

            e = enterpriseService.create(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
        }*/
    }
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestBody String username) {
        User user = userService.findByUsername(username);

        if(user.getType().equalsIgnoreCase("Creador")){
            try{
                Creator c = creatorService.index(user.getId());
                return new ResponseEntity<>(c, HttpStatus.OK);
            }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        else if(user.getType().equalsIgnoreCase("Empresa")){
            try{
                Enterprise e = enterpriseService.index(user.getId());
                return new ResponseEntity<>(e, HttpStatus.OK);
            }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
        }
    }

    @PostMapping("/profile/modify")
    public ResponseEntity<User> modify(@RequestBody User user){

        //Utilizado create de creator/enterprise para guardar en la BBDD mediante el repositorio

        if(user.getType().equalsIgnoreCase("Creador")){
            try{
                Creator c = creatorService.index(user.getId());
                c = creatorService.create(c);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        else if(user.getType().equalsIgnoreCase("Empresa")){
            try{
                Enterprise e = enterpriseService.index(user.getId());
                e = enterpriseService.create(e);
                return new ResponseEntity<>(e, HttpStatus.OK);
            }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
        }

    }


    @GetMapping("/user")
    public ResponseEntity<UserDetails> userAuth(){
        SecurityContext sc = SecurityContextHolder.getContext();
        org.springframework.security.core.Authentication auth = sc.getAuthentication();
        Object principal= null;
        if (auth != null) {
            principal = auth.getPrincipal();
            return principal instanceof UserDetails ? new ResponseEntity<>((UserDetails)principal,HttpStatus.OK) : null;
        }

        return null;
    }

}
