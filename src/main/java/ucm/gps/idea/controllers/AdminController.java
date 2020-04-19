package ucm.gps.idea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.IdeaService;
import ucm.gps.idea.services.UserService;

@Secured("ROLE_ADMIN")
@Controller
public class AdminController {

    @Autowired
    UserService adminService;

    @Autowired
    IdeaService ideaService;

    @Autowired
    EnterpriseService enterpriseService;

    
    @PostMapping("/enterprises/{enterpriseID}/ideas/{ideaID}")
    public ResponseEntity<Enterprise> accept(@PathVariable Integer enterpriseID, @PathVariable Integer ideaID) throws Exception {

        Enterprise enterprise = enterpriseService.index(enterpriseID);
        Idea idea = ideaService.index(ideaID);

        idea.setEnterprise(enterprise);

        ideaService.save(idea);

        return new ResponseEntity<>(enterpriseService.create(enterprise), HttpStatus.OK);
    }

    @PostMapping("api/admin/ideas/{ideaID}/confirmar")
    public ResponseEntity<Idea> confirmar(@PathVariable Integer ideaID) throws Exception {

        Idea idea = ideaService.index(ideaID);

        idea.setState("Confirmado");

        ideaService.save(idea);

        return new ResponseEntity<>(idea, HttpStatus.OK);
    }

    @PostMapping("api/admin/ideas/{ideaID}/borrar")
    public ResponseEntity<Idea> borrar(@PathVariable Integer ideaID) throws Exception {

        Idea idea = ideaService.index(ideaID);

        idea.setState("Borrado");
        idea.setActive(false);

        ideaService.save(idea);

        return new ResponseEntity<>(idea, HttpStatus.OK);
    }

}
