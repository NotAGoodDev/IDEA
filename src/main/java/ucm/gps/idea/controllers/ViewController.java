package ucm.gps.idea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.services.UserService;

import java.security.Principal;

@Controller
public class ViewController {

    @Autowired
    UserService userService;


    // Global

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CREATOR', 'ROLE_ENTERPRISE')")
    @GetMapping(value = {"/", "", "/home"})
    public String home(Principal principal) {
        if(principal == null){
            return "/views/index.html";
        }
        User user = userService.findByUsername(principal.getName());

        switch (user.getRoles().get(0).getName()){
            case "ROLE_CREATOR":
                return "/views/creator/index.html";
            case "ROLE_ENTERPRISE":
                return "/views/enterprise/index.html";
            case "ROLE_ADMIN":
                return "views/admin/index.html";
        }
        return "views/error/501.html";
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

    @GetMapping(value = "/creator/ideas")
    public String creatorIdeas() {
        return "/views/creator/ideas.html";
    }

    @GetMapping(value = "/creator/viewIdea")
    public String creatorViewIdea() {
        return "/views/creator/viewIdea.html";
    }

    @GetMapping(value = "/creator/profile")
    public String creatorProfile() {
        return "/views/creator/profile.html";
    }

    @GetMapping(value = "/creator/newIdea")
    public String creatorNewIdea() {
        return "/views/creator/newIdea.html";
    }

    @GetMapping(value = "/creator/deal/{idIdea}")
    public String dealC() {
        return "/views/creator/deal.html";
    }


    // Enterprise

    @GetMapping(value = "/enterprise/{idEnterprise}/viewIdea/{idIdea}")
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

    @GetMapping(value = "/enterprise/deal/{idIdea}")
    public String dealE() {
        return "/views/enterprise/deal.html";
    }

    @GetMapping(value = "/enterprise/payment")
    public String payment() {
        return "/views/enterprise/payment.html";
    }
}
