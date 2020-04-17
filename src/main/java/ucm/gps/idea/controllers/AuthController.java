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
import ucm.gps.idea.services.EmailService;
import ucm.gps.idea.services.RoleService;
import ucm.gps.idea.services.UserService;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmailService emailService;


    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody ModelUser regUser) {
        // NOTA: De front nos llega "Creador" o "Empresa"

        User user = null;
        List<Role> userRoles = new ArrayList<Role>();
        Role role = null;
        Date creatorDate = null;

        try{
            switch (regUser.getType()){
                case "Creador":

                    creatorDate = new SimpleDateFormat("yy-mm-dd").parse(regUser.getBirthDate());

                    System.out.println(encoder.encode(regUser.getPassword()));

                    String username = regUser.getUsername();
                    user = new Creator(username, encoder.encode(regUser.getPassword()),
                            true, regUser.getEmail(), regUser.getName(), regUser.getLastName(), creatorDate,
                            regUser.getTelephone(), regUser.getAddress());

                    user = userService.save(user);

                    role = new Role();
                    role.setUserId(user.getId());
                    role.setName("ROLE_CREATOR");
                    role = roleService.save(role);
                    userRoles.add(role);
                    user.setRoles(userRoles);

                    break;
                case "Empresa":
                    user = new Enterprise(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                            true, regUser.getEmail(), regUser.getName(), regUser.getCif(), regUser.getAddress(),
                            regUser.getTelephone(), regUser.getCardNumber(), regUser.getRemaining_ideas());

                    user = userService.save(user);

                    role = new Role();
                    role.setUserId(user.getId());
                    role.setName("ROLE_ENTERPRISE");
                    role = roleService.save(role);
                    userRoles.add(role);
                    user.setRoles(userRoles);
                    break;
                case "Admin":
                    user = new User();

                    user.setUsername(regUser.getUsername());
                    user.setPassword(encoder.encode(regUser.getUsername()));
                    user.setActive(true);
                    user.setEmail(regUser.getEmail());
                    user.setName(regUser.getName());
                    user.setAddress(regUser.getAddress());
                    user.setTelephone(regUser.getTelephone());

                    user = userService.save(user);

                    role = new Role();
                    role.setUserId(user.getId());
                    role.setName("ROLE_ADMIN");
                    role = roleService.save(role);
                    userRoles.add(role);
                    user.setRoles(userRoles);

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



    @PostMapping ("/sendEmailResetPassword")
    public ResponseEntity<?> sendEmailResetPassword(@RequestParam String email){

        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            // Create token
            final String token = UUID.randomUUID().toString();

            existingUser.setToken(token);
            userService.save(existingUser);
            emailService.sendMail(email, token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }



    @PostMapping ("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam String newPassword, @RequestParam String token ){

        User existingUser =  userService.findByToken(token);
        if (existingUser != null) {
            existingUser.setPassword(encoder.encode(newPassword));
            existingUser.setToken(null);
            userService.save(existingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
