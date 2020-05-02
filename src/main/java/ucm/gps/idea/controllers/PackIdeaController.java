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
import ucm.gps.idea.models.ModelPackToBuy;
import ucm.gps.idea.services.EnterpriseService;
import ucm.gps.idea.services.PackIdeaService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/packages")
public class PackIdeaController {

    private static final Logger logger = LoggerFactory.getLogger(PackIdeaController.class);

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    PackIdeaService packIdeaService;

    @GetMapping("")
    public ResponseEntity<List<PackIdea>> list() {
        List<PackIdea> packages = packIdeaService.listAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    // Para calcular el precio final dado el numero de ideas y el descuento que se le tiene que aplicar
    @GetMapping("/calculateFinalPrice")
    public ResponseEntity<Double> finalPrice(@RequestBody ModelPackToBuy modelPackToBuy) {
        // Calculamos el total de la compra y lo devolvemos para que pague
        logger.info("antes de calcular");
        double total = packIdeaService.calculatePrice(modelPackToBuy);
        logger.info(modelPackToBuy.toString() + " " + total);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    // Para actualizar el numero de ideas cuando una empresa compre un pack de ideas
    @PutMapping("/updateNumIdeas")
    public ResponseEntity<Enterprise> updateNumIdeas(@RequestBody Integer numIdeasToBuy, Principal principal){
        logger.info("antes de empresa");
        Enterprise enterprise = enterpriseService.findByName(principal.getName());
        enterprise.setRemainingIdeas(enterprise.getRemainingIdeas() + numIdeasToBuy);
        Enterprise ret = enterpriseService.create(enterprise);
        logger.info("actualizada empresa");
        return new ResponseEntity<>(ret, ret != null ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    @GetMapping("/getPack")
    public ResponseEntity<PackIdea> getPack(@RequestBody String packName){
        PackIdea packIdea= packIdeaService.findByName(packName);

        if(packIdea == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(packIdea,HttpStatus.OK);
    }

    // Para en front mostrar el descuento que obtendria si compra X ideas.
    @GetMapping("/getDiscounts")
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
