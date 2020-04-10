package ucm.gps.idea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Role;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.models.ModelUser;
import ucm.gps.idea.services.RoleService;
import ucm.gps.idea.services.UserService;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody ModelUser regUser) {
        // NOTA: De front nos llega "Creador" o "Empresa"

        User user = null;
        List<Role> userRoles = new ArrayList<Role>();
        Role elRol = null;
        Date creatorDate = null;

        try{
            switch (regUser.getType()){
                case "Creador":
                    
                    creatorDate = new SimpleDateFormat("yy-mm-dd").parse(regUser.getBirthDate());
                
                    user = new Creator(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                            true, regUser.getEmail(), regUser.getName(), regUser.getLastName(), creatorDate,
                            regUser.getTelephone(), regUser.getAddress());

                    user = userService.save(user);

                    elRol = new Role();
                    elRol.setUserId(user.getId());
                    elRol.setName("ROLE_CREATOR");
                    elRol = roleService.save(elRol);
                    userRoles.add(elRol);
                    user.setRoles(userRoles);
                
                    break;
                case "Empresa":
                    user = new Enterprise(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                            true, regUser.getEmail(), regUser.getName(), regUser.getCif(), regUser.getAddress(),
                            regUser.getTelephone(), regUser.getCardNumber(), regUser.getRemaining_ideas());

                    user = userService.save(user);

                    elRol = new Role();
                    elRol.setUserId(user.getId());
                    elRol.setName("ROLE_ENTERPRISE");
                    elRol = roleService.save(elRol);
                    userRoles.add(elRol);
                    user.setRoles(userRoles);

                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/sessionId")
    public ResponseEntity<?> userIdAuth(Principal principal){

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            return new ResponseEntity<>(user.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/session")
    public ResponseEntity<?> userAuth(Principal principal){

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
