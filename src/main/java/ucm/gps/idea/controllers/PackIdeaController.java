package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.PackIdea;
import ucm.gps.idea.entities.User;
import ucm.gps.idea.models.ModelPackToBuy;
import ucm.gps.idea.services.PackIdeaService;
import ucm.gps.idea.services.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/packages")
public class PackIdeaController {

    private static final Logger logger = LoggerFactory.getLogger(PackIdeaController.class);
    private  static final Double PRICE_PER_IDEA = 7.0;

    @Autowired
    UserService userService;

    @Autowired
    PackIdeaService packIdeaService;

    @GetMapping("")
    public ResponseEntity<List<PackIdea>> list() {
        List<PackIdea> packages = packIdeaService.listAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @PutMapping("/buy")
    public ResponseEntity<Double> create(@RequestBody ModelPackToBuy modelPackToBuy, Principal principal) {
        // Calculamos el total de la compra, añadimos las ideas a la empresa y devolvemos el total para q pague
        double total = modelPackToBuy.getNumIdeasToBuy() * PRICE_PER_IDEA;
        total -= total * (modelPackToBuy.getDiscount() / 100.0);
        logger.info(modelPackToBuy.toString() + " " + total);

        User user = userService.findByUsername(principal.getName());
        ((Enterprise)user).setRemainingIdeas(((Enterprise)user).getRemainingIdeas() + modelPackToBuy.getNumIdeasToBuy());

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/getPack")
    public ResponseEntity<PackIdea> getPack(@RequestBody String packName){
        PackIdea packIdea= packIdeaService.findByName(packName);

        if(packIdea == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(packIdea,HttpStatus.OK);
    }

    @GetMapping("/getDiscount")
    public ResponseEntity<Map<Pair<Integer, Integer>, Integer>> getDiscount(){ //@RequestBody Integer numIdeas){
        Map<Pair<Integer, Integer>, Integer> discountTable = new HashMap<>();

        discountTable.put(Pair.of(40, 250), 5);
        discountTable.put(Pair.of(250, 2000), 10);
        discountTable.put(Pair.of(2000, 3500), 15);
        discountTable.put(Pair.of(3500, 5000), 20);

        return new ResponseEntity<>(discountTable, HttpStatus.OK);
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
