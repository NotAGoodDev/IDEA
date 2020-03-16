package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.services.IdeaService;

import java.util.List;

@RestController
@RequestMapping("api/ideas")
class IdeaController {

    @Autowired
    IdeaService ideaService;

    private static final Logger logger = LoggerFactory.getLogger(IdeaController.class);

    @GetMapping("")
    public ResponseEntity<List<Idea>> list(){
        List<Idea> listIdeas = ideaService.list();
        logger.info("se ha hecho una peticion de listar");
        return new ResponseEntity<>(listIdeas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Idea> index(@PathVariable Integer id){
        try{
            Idea idea = ideaService.index(id);
            return new ResponseEntity<>(idea,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed id: %i", id));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Idea>> listByDate(){
        List<Idea> listIdeas = ideaService.listByDate();
        logger.info("se ha hecho una peticion de listar por fecha de creacion");
        return new ResponseEntity<>(listIdeas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ideaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Idea> create(@RequestBody Idea idea) {
        return new ResponseEntity<>(ideaService.save(idea), HttpStatus.OK);
    }
}