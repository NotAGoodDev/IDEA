package ucm.gps.idea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.models.ModelUser;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.RoleService;
import ucm.gps.idea.services.UserService;

import java.security.Principal;

@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CreatorService creatorService;
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("profile")
    public ResponseEntity<?> getProfile(Principal principal) {

        User user = userService.findByUsername(principal.getName());

        try{
            switch(user.getType().toLowerCase()){
                case "Creador":
                    Creator creator = creatorService.index(user.getId());
                    return new ResponseEntity<Creator>(creator, HttpStatus.OK);
                case "Empresa":
                    Enterprise enterprise = enterpriseService.index(user.getId());
                    return new ResponseEntity<Enterprise>(enterprise, HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
            }
        }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }

    }


    @PutMapping("/profiles/")
    public ResponseEntity<User> modify(@RequestBody ModelUser moduser, Principal principal){

        User user = userService.findByUsername(principal.getName());

        if(user.getType().equalsIgnoreCase("Creador")){
            try{
                user.setAddress(moduser.getAddress());
                user.setEmail(moduser.getEmail());
                user.setName(moduser.getName());
                user.setTelephone(moduser.getTelephone());
                userService.save(user);

                Creator c = creatorService.index(user.getId());
                c.setLastName(moduser.getLastName());
                c = creatorService.create(c);

                return new ResponseEntity<>(c, HttpStatus.OK);
            }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        else if(user.getType().equalsIgnoreCase("Empresa")){
            try{
                user.setAddress(moduser.getAddress());
                user.setEmail(moduser.getEmail());
                user.setName(moduser.getName());
                user.setTelephone(moduser.getTelephone());
                userService.save(user);

                Enterprise e = enterpriseService.index(user.getId());
                e.setCreditCard(moduser.getCardNumber());
                e = enterpriseService.create(e);

                return new ResponseEntity<>(e, HttpStatus.OK);
            }catch (Exception e){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
        }

    }

}
