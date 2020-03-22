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

        /* Falta hacerlo entero */
    @GetMapping(value = {"/", "", "/home"})
    public String home() {
        return "/views/index.html";
    }

        /*Falta hacerlo entero*/
    @GetMapping(value = "/who")
    public String who() {
        return "/views/who.html";
    }

        /*Al ser responsive no se muestra el nombre*/
    @GetMapping(value = "/header")
    public String header(Authentication authentication) {
        if(authentication == null)
            return "/views/header.html";
        else
            return "/views/headerAuth.html";
    }

        /*Faltaria enlazar links*/
    @GetMapping(value = "/footer")
    public String footer() {
        return "/views/footer.html";
    }


    // User
        /*Done*/
    @GetMapping(value = "/register")
    public String register() {
        return "/views/auth/register.html";
    }

    /* Mostrar mensaje error si falla */
    @GetMapping(value = "/login")
    public String login() {
        return "/views/auth/login.html";
    }


    // Creator

    /*Evitar PopUp*/
        /*Hacerlo dinamico*/
        /*Poner espaciado*/
    @GetMapping(value = {"/creator","/creator/home","/creator/"})
    public String creatorIndex() {
        return "/views/creator/index.html";
    }

        /* Misma estrucutra que creator/home -> Borrar */
    @GetMapping(value = "/creator/ideas")
    public String creatorIdeas() {
        return "/views/creator/ideas.html";
    }

        /*Cambiar colores*/
        /*Añadir header y footer*/
        /*Añadir CSS, absolutamente to.do falla*/
    @GetMapping(value = "/creator/viewIdea")
    public String creatorViewIdea() {
        return "/views/creator/viewIdea.html";
    }

        /*Hacer dinámico*/
        /* SE PUEDE ACCEDER DESDE EMPRESA PONIENDO LA URL*/
        /*¿Añadir imagen?*/
    @GetMapping(value = "/creator/profile")
    public String creatorProfile() {
        return "/views/creator/profile.html";
    }

        /*Añadir buscador autocompletable*/
        /*No buen responsive*/
        /*Label mal colocadas*/
        /*Botones mal colocados*/
    @GetMapping(value = "/creator/newIdea")
    public String creatorNewIdea() {
        return "/views/creator/newIdea.html";
    }


    // Enterprise
        /*En login, mostrar mensaje error si falla*/

        /*Evitar PopUp*/
        /*Hacerlo dinamico*/
        /*Poner espaciado*/
    @GetMapping(value = {"/enterprise", "/enterprise/home", "/enterprise/"})
    public String enterpriseIndex() {
        return "/views/enterprise/index.html";
    }

    @GetMapping(value = "/enterprise/viewIdea")
    public String enterpriseIndexIdea() {
        return "/views/enterprise/viewIdea.html";
    }

        /* No centrado */
        /* No hover */
        /* No pack recomendado */
    @GetMapping(value = "/enterprise/buyPack")
    public String comprarPack() {
        return "/views/enterprise/packs.html";
    }


        /* Falta hacerlo dinamico */
        /* SE PUEDE ACCEDER DESDE CREATOR PONIENDO LA URL*/

    @GetMapping(value = "/enterprise/profile")
    public String enterpriseProfile() {
        return "/views/enterprise/profile.html";
    }

    /*Faltaria hacerlo dinamico*/
    @GetMapping(value = "/enterprise/deal")
    public String deal() {
        return "/views/enterprise/deal.html";
    }
}
