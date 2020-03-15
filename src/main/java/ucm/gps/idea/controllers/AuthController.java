package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucm.gps.idea.entities.Role;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.models.RegisterUser;
import ucm.gps.idea.services.RoleService;
import ucm.gps.idea.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody RegisterUser regUser) {

        User user = new User();
        user.setUsername(regUser.getUsername());
        user.setPassword(encoder.encode(regUser.getPassword()));
        user.setEmail(regUser.getEmail());
        user.setType(regUser.getType());
        user = userService.save(user);

        for( String name : regUser.getRoles()){
            Role role = new Role();
            role.setUser_id(user.getId());
            role.setName(name);
            roleService.save(role);
        }

        user.setRoles(user.getRoles());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
