package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.EnterpriseService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    //Aqui irian todas las peticiones de login y register
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping("/enterprise/register")
    public ResponseEntity<Enterprise> create(@RequestBody Enterprise enterprise) {
        return new ResponseEntity<Enterprise>(enterpriseService.create(enterprise), HttpStatus.OK);
    }

    @PostMapping("/creator/register")
    public ResponseEntity<Creator> create(@RequestBody Creator creator) {
        return new ResponseEntity<Creator>(creatorService.create(creator), HttpStatus.OK);
    }
    
    @PostMapping(value="/enterprise/login", consumes = "application/json")
    public String login(@RequestBody Enterprise enterprise) {
    	return "redirect:/enterprise.html";
    }


    @PostMapping(value="/creator/login", consumes = "application/json")
    public String login(@RequestBody Creator creator) {
    	logger.info(creator.getEmail() + " " + creator.getPassword());
    	return "redirect:/creator.html";
    }

}