package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    // Global

    @GetMapping(value = {"/", "", "/home"})
    public String home() {
        return "/views/index.html";
    }

    @GetMapping(value = "/who")
    public String who() {
        return "/views/who.html";
    }

    @GetMapping(value = "/header")
    public String header(Authentication authentication) {
        if(authentication == null)
            return "/views/header.html";
        else
            return "/views/headerAuth.html";
    }

    @GetMapping(value = "/footer")
    public String footer() {
        return "/views/footer.html";
    }



    @GetMapping(value = "/deal")
    public String deal() {
        return "/views/deal.html";
    }

    // User

    @GetMapping(value = "/register")
    public String register() {
        return "/views/auth/register.html";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/views/auth/login.html";
    }


    // Creator
    @GetMapping(value = {"/creator","/creator/home","/creator/"})
    public String creatorIndex() {
        return "/views/creator/index.html";
    }

    @GetMapping(value = "/creator/ideas")
    public String creatorIdeas() {
        return "/views/creator/ideas.html";
    }

    @GetMapping(value = "/creator/viewIdea")
    public String creatorViewIdea() {
        return "/views/creator/viewIdea.html";
    }


    @GetMapping(value = "/creator/acceptedIdeas")
    public String creatorAcceptedtIdeas() {
        return "/views/creator/accepted-ideas.html";
    }

    @GetMapping(value = "/creator/profile")
    public String creatorProfile() {
        return "/views/creator/profile.html";
    }


    @GetMapping(value = "/creator/newIdea")
    public String creatorNewIdea() {
        return "/views/creator/newIdea.html";
    }


    // Enterprise
    @GetMapping(value = {"/enterprise","/enterprise/home","/enterprise/"})
    public String enterpriseIndex() {
        return "/views/enterprise/index.html";
    }

    @GetMapping(value = "/enterprise/viewIdea")
    public String enterpriseIndexIdea() {
        return "/views/enterprise/viewIdea.html";
    }

    @GetMapping(value = "/enterprise/buyPack")
    public String comprarPack() {
        return "/views/enterprise/packs.html";
    }

    @GetMapping(value = "/enterprise/profile")
    public String enterpriseProfile() {
        return "/views/enterprise/profile.html";
    }



}
