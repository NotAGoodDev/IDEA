package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ucm.gps.idea.entities.PackIdea;
import ucm.gps.idea.models.ModelPackToBuy;
import ucm.gps.idea.services.PackIdeaService;

import java.util.List;

@RestController
@RequestMapping("api/packages")
public class PackIdeaController {

    private static final Logger logger = LoggerFactory.getLogger(PackIdeaController.class);

    @Autowired
    PackIdeaService packIdeaService;

    @GetMapping("")
    public ResponseEntity<List<PackIdea>> list(){
        List<PackIdea> packages = packIdeaService.listAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<?> create(@RequestBody ModelPackToBuy modelPackToBuy) {
        logger.info(modelPackToBuy.toString());
        // DATOS RELATIVOS A LA COMPRA
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getPack")
    public ResponseEntity<PackIdea> getPack(@RequestBody String packName){
        PackIdea packIdea= packIdeaService.findByName(packName);

        if(packIdea == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(packIdea,HttpStatus.OK);
    }

    // En formato String JSON por si ayuda en front
    /*@GetMapping("")
    public ResponseEntity<List<String>> list(){
        List<PackIdea> packages = packIdeaService.listAll();
        List<String> ret = new ArrayList<String>();

        for(PackIdea pack : packages)
            ret.add(pack.toString());

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }*/
}
