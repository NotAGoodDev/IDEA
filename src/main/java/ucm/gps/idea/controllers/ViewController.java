package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    // Global

    @GetMapping(value={ "/", "", "/home" })
    public String home(){
        return "/index.html";
    }

    @GetMapping(value="/who")
    public String who(){
        return "/who.html";
    }

    // User

    @GetMapping(value="/register")
    public String register(){
        return "/register.html";
    }

    // Creator
    @GetMapping(value="/creator")
    public String creatorIndex(){
        return "/creator/index.html";
    }

    @GetMapping(value="/creator/ideas")
    public String creatorIdeas(){
        return "/creator/ideas.html";
    }

    // Enterprise
    @GetMapping(value="/enterprise")
    public String enterpriseIndex(){
        return "/enterprise/index.html";
    }










}
