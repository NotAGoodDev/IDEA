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
import java.util.Date;
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
    public ResponseEntity<User> create(@RequestBody RegisterUser regUser) {
        // NOTA: De front nos llega "Creador" o "Empresa"

        User user = null;
        List<Role> userRoles = new ArrayList<Role>();
        Role elRol = null;
        Date creatorDate = null;

        switch (regUser.getType()){
            case "Creador":
                try{
                    creatorDate = new SimpleDateFormat("yy-mm-dd").parse(regUser.getBirthDate());
                }catch (Exception e){
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
                user = new Creator(regUser.getUsername(), encoder.encode(regUser.getPassword()), regUser.getType(), true,
                        regUser.getEmail(), regUser.getName(), regUser.getLastName(), creatorDate,
                        regUser.getTelephone(), regUser.getAddress());

                user = userService.save(user);

                elRol = new Role();
                elRol.setUser_id(user.getId());
                elRol.setName("ROLE_USER");
                elRol = roleService.save(elRol);
                userRoles.add(elRol);
                user.setRoles(userRoles);

                break;
            case "Empresa":
                user = new Enterprise(regUser.getUsername(), encoder.encode(regUser.getPassword()), regUser.getType(), true,
                        regUser.getEmail(), regUser.getName(), regUser.getCif(), regUser.getAddress(),
                        regUser.getTelephone(), regUser.getCardNumber(), regUser.getRemaining_ideas());

                user = userService.save(user);

                elRol = new Role();
                elRol.setUser_id(user.getId());
                elRol.setName("ROLE_USER");
                elRol = roleService.save(elRol);
                userRoles.add(elRol);
                user.setRoles(userRoles);

                break;
            default:
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
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
    public ResponseEntity<User> modify(@RequestBody RegisterUser Reguser){

        User user = userService.findByUsername(Reguser.getUsername());

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
