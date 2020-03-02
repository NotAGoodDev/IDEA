package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value="/home", method = RequestMethod.GET )
    public String home(){
        return "/home.html";
    }

    @RequestMapping(value="/enterprise", method = RequestMethod.GET )
    public String enterprise(){
        return "/enterprise.html";
    }

    @RequestMapping(value="/creator", method = RequestMethod.GET )
    public String creator(){
        return "/creator.html";
    }

    @RequestMapping(value="/registrar-creator", method = RequestMethod.GET )
    public String registrarCreator(){
        return "/registrar-creador.html";
    }

    @RequestMapping(value="/quienes-somos", method = RequestMethod.GET )
    public String quienesSomos(){
        return "/quienes-somos.html";
    }
}
