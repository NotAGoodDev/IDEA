package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.IdeaService;

import java.util.List;

@RestController
@RequestMapping("api/enterprises")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    IdeaService ideaService;

    private static final Logger logger = LoggerFactory.getLogger(EnterpriseController.class);

    @GetMapping("/")
    public ResponseEntity<List<Enterprise>> list(){
        List<Enterprise> listEnterprise = enterpriseService.list();
        return new ResponseEntity<>(listEnterprise, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Enterprise> index(@PathVariable Integer id){
        try{
            Enterprise enterprise = enterpriseService.index(id);
            return new ResponseEntity<>(enterprise,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed id: %i", id));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Enterprise> findByName(@PathVariable String name){

        try{
            Enterprise enterprise =enterpriseService.findByName(name);
            return new ResponseEntity<>(enterprise,HttpStatus.OK);
        }catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed name: %i", name));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        enterpriseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Enterprise> create(@RequestBody Enterprise enterprise) {
        return new ResponseEntity<>(enterpriseService.create(enterprise), HttpStatus.OK);
    }


    @PostMapping("/enterprises/{enterpriseID}/ideas/{ideaID}")
    public ResponseEntity<Enterprise> create(@PathVariable Integer enterpriseID, @PathVariable Integer ideaID) throws Exception {

        Enterprise enterprise = enterpriseService.index(enterpriseID);
        Idea idea = ideaService.index(ideaID);

        idea.setEnterprise(enterprise);

        ideaService.save(idea);

        return new ResponseEntity<>(enterpriseService.create(enterprise), HttpStatus.OK);
    }

}
