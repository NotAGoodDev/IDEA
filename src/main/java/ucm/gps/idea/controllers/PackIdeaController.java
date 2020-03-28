package ucm.gps.idea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ucm.gps.idea.entities.PackIdea;
import ucm.gps.idea.services.PackIdeaService;

import java.util.Date;
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

    @PostMapping("/buy")
    public ResponseEntity<Boolean> create(@PathVariable Integer numTarjeta, @PathVariable Integer codigoValidacion,
                                    @PathVariable String propietarioTarjeta, @PathVariable Date fechaExpiracion) {

        Integer tarjeta = numTarjeta;
        Integer ccv = codigoValidacion;
        String titular = propietarioTarjeta;
        Date fecha = fechaExpiracion;

        // Falta de hacer la parte de VISA

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping("/getPack")
    public ResponseEntity<PackIdea> getPack(@PathVariable String packName ){
        PackIdea packIdea= packIdeaService.findByName(packName);
        if(packIdea == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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
