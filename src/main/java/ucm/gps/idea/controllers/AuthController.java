package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /* TODO RegisterUser meterle todos los atributos
    *   Un controller puede llamar a otro controller en Spring (buena praxis)? O mejor hacerlo mediante Services
    *   Roles, como hacerlo
    *   Ver metodo de tablas en la BD pues Creator/Enterprise extends User */

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
    public ResponseEntity<User> create(@RequestBody User regUser) {
        // NOTA: De front nos llega "Creador" o "Empresa"

        User user = new User();

        user.setUsername(regUser.getUsername());
        user.setPassword(encoder.encode(regUser.getPassword()));
        user.setEmail(regUser.getEmail());
        user.setType(regUser.getType());
        user.setActive(true);
        user = userService.save(user);

        for(Role rol : regUser.getRoles()){
            Role role = new Role();
            role.setUser_id(user.getId());
            role.setName(rol.getName());
            roleService.save(role);
        }

        user.setRoles(user.getRoles());

        if(user.getType().equalsIgnoreCase("Creador")){
            Creator c = new Creator(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                    regUser.getType(), true, regUser.getEmail(), ((Creator)regUser).getName(),
                    ((Creator)regUser).getLastName(), ((Creator)regUser).getBirthDate(),
                    ((Creator)regUser).getTelephone(), ((Creator)regUser).getAddress());
            c = creatorService.create(c);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        else if(user.getType().equalsIgnoreCase("Empresa")){
            Enterprise e = new Enterprise(regUser.getUsername(), encoder.encode(regUser.getPassword()),
                    regUser.getType(), true, regUser.getEmail(), ((Creator)regUser).getName(),
                    ((Enterprise)regUser).getCIF(), ((Enterprise)regUser).getAddress(),
                    ((Enterprise)regUser).getTelephone(), ((Enterprise)regUser).getCreditCard(),
                    ((Enterprise)regUser).getRemainingIdeas());
            e = enterpriseService.create(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); //403
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<User> create(@RequestBody String username) {
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

}
