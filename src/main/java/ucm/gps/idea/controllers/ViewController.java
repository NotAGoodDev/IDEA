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
        return "/views/index.html";
    }

    @GetMapping(value="/who")
    public String who(){
        return "/views/who.html";
    }

    @GetMapping(value="/header")
    public String header(){
        return "/views/header.html";
    }

    @GetMapping(value="/footer")
    public String footer(){
        return "/views/footer.html";
    }

    // User

    @GetMapping(value="/register")
    public String register(){
        return "/views/auth/register.html";
    }

    // Creator
    @GetMapping(value="/creator")
    public String creatorIndex(){
        return "/views/creator/index.html";
    }

    @GetMapping(value="/creator/ideas")
    public String creatorIdeas(){
        return "/views/creator/ideas.html";
    }

    @GetMapping(value="/creator/profile")
    public String creatorProfile(){
        return "/views/creator/profile.html";
    }

    @GetMapping(value="/creator/nuevaIdea")
    public String creatorNewIdea(){
        return "/views/creator/newIdea.html";
    }



    // Enterprise
    @GetMapping(value="/enterprise")
    public String enterpriseIndex(){
        return "/views/enterprise/index.html";
    }

    @GetMapping(value="/enterprise/compraPacks")
    public String comprarPack(){
        return "/views/enterprise/packs.html";
    }










}
