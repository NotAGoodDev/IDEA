package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value="/", method = RequestMethod.GET )
    public String home(){
        return "/index.html";
    }

    @RequestMapping(value="/enterprise", method = RequestMethod.GET )
    public String enterprise(){
        return "/enterprise/enterprise.html";
    }

    @RequestMapping(value="/creator", method = RequestMethod.GET )
    public String creator(){
        return "/creator/creator.html";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET )
    public String registrar(){
        return "/register.html";
    }

    @RequestMapping(value="/register-creator", method = RequestMethod.GET )
    public String registrarCreador(){
        return "/creator/register.html";
    }

    @RequestMapping(value="/register-enterprise", method = RequestMethod.GET )
    public String registrarEmpresa(){ return "/enterprise/register.html"; }

    @RequestMapping(value="/who", method = RequestMethod.GET )
    public String quienesSomos(){
        return "/who.html";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET )
    public String login(){
        return "/login.html";
    }

    @RequestMapping(value="/myideas", method = RequestMethod.GET )
    public String my_ideas(){
        return "/creator/my-ideas.html";
    }
}
