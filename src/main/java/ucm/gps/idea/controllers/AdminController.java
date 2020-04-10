package ucm.gps.idea.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.services.AdminService;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.IdeaService;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    IdeaService ideaService;

    @Autowired
    EnterpriseService enterpriseService;



    @PostMapping("/enterprises/{enterpriseID}/ideas/{ideaID}")
    public ResponseEntity<Enterprise> acept(@PathVariable Integer enterpriseID, @PathVariable Integer ideaID) throws Exception {

        Enterprise enterprise = enterpriseService.index(enterpriseID);
        Idea idea = ideaService.index(ideaID);

        idea.setEnterprise(enterprise);

        ideaService.save(idea);

        return new ResponseEntity<>(enterpriseService.create(enterprise), HttpStatus.OK);
    }
}
