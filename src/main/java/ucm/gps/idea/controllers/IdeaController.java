package ucm.gps.idea.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.*;
import ucm.gps.idea.models.IdeaModel;
import ucm.gps.idea.models.ModelUser;
import ucm.gps.idea.services.CategoryService;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.IdeaService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/ideas")
class IdeaController {

    @Autowired
    IdeaService ideaService;
    @Autowired
    CreatorService creatorService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(IdeaController.class);

    @GetMapping("")
    public ResponseEntity<List<Idea>> list() {
        List<Idea> listIdeas = ideaService.list();
        logger.info("se ha hecho una peticion de listar");
        return new ResponseEntity<>(listIdeas, HttpStatus.OK);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Idea> index(@PathVariable Integer id) {
        try {
            Idea idea = ideaService.index(id);
            return new ResponseEntity<>(idea, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed id: %i", id));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    */


    @GetMapping("/{id}")
    public ResponseEntity<IdeaModel> ideaToFront(@PathVariable Integer id) {
        try {
            Idea idea = ideaService.index(id);


            IdeaModel ideaModel = new IdeaModel(
                    idea.getDeal() == null ? -1 : idea.getDeal().getId(),
                    idea.getCreator().getName() + " " + idea.getCreator().getLastName(),
                    idea.getCreator().getUsername(),
                    idea.getEnterprise().getName(),
                    idea.getCategory().getName(),
                    idea.getTitle(),
                    idea.getDescription(),
                    idea.getSummary(),
                    idea.getCreatedAt(),
                    idea.getState(),
                    idea.getDeal() != null && idea.getDeal().isCAgree() && idea.getDeal().isEAgree(),
                    idea.isActive() 
            );

            return new ResponseEntity<>(ideaModel, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Read an specific Idea failed id:" + id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Idea>> listByDate() {
        List<Idea> listIdeas = ideaService.listByDate();
        logger.info("se ha hecho una peticion de listar por fecha de creacion");
        return new ResponseEntity<>(listIdeas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ideaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @PostMapping("/")
    public ResponseEntity<Idea> create(@RequestBody ModelIdea model) {
        ObjectMapper obMapper = new ObjectMapper();

        Idea idea = obMapper.readValues(model, Idea.class);

        Creator creator = creatorService.findByUsername(model.getCreatorUsername());
        Enterprise enterprise = enterpriseService.findByName(model.getEnterpriseUsername());
        Category category = categoryService.findByName(model.getCategoryName());
        Idea idea = ideaService.modelToIdea(model, creator, enterprise, category);

        return new ResponseEntity<>(ideaService.save(idea), HttpStatus.OK);
    }
    */

    @PostMapping("/")
    public ResponseEntity<Idea> create(@RequestBody String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Idea idea = objectMapper.readValue(json, Idea.class);

        //Leemos los campos para asignar las relaciones
        JsonNode root = objectMapper.readTree(json);

        idea.setActive(true);
        idea.setEnterprise(enterpriseService.findByName(root.get("enterprise").asText()));
        idea.setCreator(creatorService.findByUsername(root.get("creator").asText()));
        idea.setCategory(categoryService.findByName(root.get("category").asText()));

        return new ResponseEntity<>(ideaService.save(idea), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Idea> modify(@RequestBody String json) throws JsonProcessingException{

        ObjectMapper objectMapper = new ObjectMapper();
        Idea idea = objectMapper.readValue(json, Idea.class);
        logger.info(json);
        //Leemos los campos para asignar las relaciones
        JsonNode root = objectMapper.readTree(json);

        idea.setActive(true);
        idea.setEnterprise(enterpriseService.findByName(root.get("enterprise").asText()));
        idea.setCreator(creatorService.findByUsername(root.get("creator").asText()));
        idea.setCategory(categoryService.findByName(root.get("category").asText()));
        Idea i = ideaService.save(idea);

        return new ResponseEntity<>(i, HttpStatus.OK);
    }


    @PostMapping("/ideas/{ideaID}/enterprises/{enterpriseID}")
    public ResponseEntity<Idea> send(@PathVariable Integer ideaID, @PathVariable Integer enterpriseID) throws Exception {
        return new ResponseEntity<Idea>(ideaService.send(ideaID, enterpriseID), HttpStatus.OK);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<Set<Idea>> findByCategory(@PathVariable String name){

        try{
            Category category = categoryService.findByName(name);
            return new ResponseEntity<Set<Idea>>(category.getIdea(), HttpStatus.OK);

        }catch(Exception e){
            logger.error(String.format("Read an specific category failed category: %s", name));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/state/{state}")
    public ResponseEntity<List<Idea>> findByState(@PathVariable String state){

        List<Idea> listIdeas = ideaService.listByState(state);
        logger.info("se ha hecho una peticion de listar por fecha de creacion");
        return new ResponseEntity<>(listIdeas, HttpStatus.OK);
    }

}