package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.IdeaService;

import java.util.List;

@RestController
@RequestMapping("api/creator")
public class CreatorController {

    @Autowired
    CreatorService creatorService;
    @Autowired
    IdeaService ideaService;

    private static final Logger logger = LoggerFactory.getLogger(CreatorController.class);

    @GetMapping("/")
    public ResponseEntity<List<Creator>> list(){
        List<Creator> listCreator = creatorService.list();
        logger.info("se ha hecho una peticion de listar type creator");
        return new ResponseEntity<>(listCreator, HttpStatus.OK);
    }

    @GetMapping(path="/id/{id}")
    public ResponseEntity<Creator> index(@PathVariable Integer id){
        try{
            Creator creator = creatorService.index(id);
            return new ResponseEntity<>(creator,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed id: %i", id));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Creator> findByUsername(@PathVariable String name){

        try{
            Creator creator = creatorService.findByUsername(name);
            return new ResponseEntity<>(creator,HttpStatus.OK);
        }catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed name: %i", name));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        creatorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Creator> create(@RequestBody Creator creator) {
        return new ResponseEntity<>(creatorService.create(creator), HttpStatus.OK);
    }


    @PostMapping("/creators/{creatorID}/ideas/{ideaID}")
    public ResponseEntity<Creator> create(@PathVariable Integer creatorID, @PathVariable Integer ideaID) throws Exception {

        Creator creator = creatorService.index(creatorID);
        Idea idea = ideaService.index(ideaID);

        idea.setCreator(creator);

        ideaService.save(idea);

        return new ResponseEntity<>(creatorService.create(creator), HttpStatus.OK);
    }

}
