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
import ucm.gps.idea.entities.User;
import ucm.gps.idea.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {


	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user) {
            user.setPassword(encoder.encode(user.getPassword()));
            User response = userService.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/login", consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody User user) {

        User response = userService.findByEmail(user.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK); //403
    }


    /*@PostMapping(value="/logout", consumes = "application/json")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {

        return "redirect:/login";
    }*/

}
