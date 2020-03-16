package ucm.gps.idea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucm.gps.idea.entities.PackIdea;
import ucm.gps.idea.services.PackIdeaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/packages")
public class PackIdeaController {

    @Autowired
    PackIdeaService packIdeaService;

    @GetMapping("")
    public ResponseEntity<List<PackIdea>> list(){
        List<PackIdea> packages = packIdeaService.listAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
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
