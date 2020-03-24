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
import ucm.gps.idea.entities.Role;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.models.ModelUser;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.RoleService;
import ucm.gps.idea.services.UserService;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CreatorService creatorService;
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("profiles")
    public ResponseEntity<?> getProfile(Principal principal) {

        User user = userService.findByUsername(principal.getName());

        List<Role> userRoles = user.getRoles();

        if(!userRoles.isEmpty()) {
            try {
                switch (userRoles.get(0).getName()) {
                    case "ROLE_CREATOR":
                        Creator creator = creatorService.index(user.getId());
                        return new ResponseEntity<>(creator, HttpStatus.OK);
                    case "ROLE_ENTERPRISE":
                        Enterprise enterprise = enterpriseService.index(user.getId());
                        return new ResponseEntity<>(enterprise, HttpStatus.OK);
                    default:
                        return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    @PutMapping("/profiles/")
    public ResponseEntity<User> modify(@RequestBody ModelUser moduser, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        List<Role> userRoles = user.getRoles();

        if(!userRoles.isEmpty()){
            try {
                switch (userRoles.get(0).getName()) {
                    case "ROLE_CREATOR":
                        user.setAddress(moduser.getAddress());
                        user.setEmail(moduser.getEmail());
                        user.setName(moduser.getName());
                        user.setTelephone(moduser.getTelephone());
                        userService.save(user);
                        Creator creator = creatorService.index(user.getId());
                        creator.setLastName(moduser.getLastName());
                        creator = creatorService.create(creator);
                        return new ResponseEntity<>(creator, HttpStatus.OK);
                    case "ROLE_ENTERPRISE":
                        user.setAddress(moduser.getAddress());
                        user.setEmail(moduser.getEmail());
                        user.setName(moduser.getName());
                        user.setTelephone(moduser.getTelephone());
                        userService.save(user);

                        Enterprise enterprise = enterpriseService.index(user.getId());
                        enterprise.setCreditCard(moduser.getCardNumber());
                        enterprise = enterpriseService.create(enterprise);

                        return new ResponseEntity<>(enterprise, HttpStatus.OK);
                    default:
                        return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
