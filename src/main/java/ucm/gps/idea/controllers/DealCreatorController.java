package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.DealDTO;
import ucm.gps.idea.services.CreatorService;
import ucm.gps.idea.services.DealService;

@RestController
@RequestMapping("/api/creator/deal")
public class DealCreatorController {

    @Autowired
    private DealService dealService;

    private static final Logger logger = LoggerFactory.getLogger(DealCreatorController.class);

    @PostMapping("/{id}")
    public ResponseEntity<DealDTO> manageDeal(@RequestBody DealDTO dealDTO) throws Exception {
        try {

            if(!dealService.index(dealDTO.getId()).isCAgree()
                    || !dealService.index(dealDTO.getId()).isEAgree())  //Si estan o no de acuerdo las dos partes
            {
                if (!equals(dealDTO, dealService.index(dealDTO.getId())))  //Si se han cambiado las condiciones (contraoferta)
                    dealService.index(dealDTO.getId()).setEAgree(false);    //La empresa no la ha aceptado

                //el creador esta de acuerdo porque ha hecho la oferta
                dealService.index(dealDTO.getId()).setCAgree(true);
            }

            return new ResponseEntity<>(dealDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    boolean equals(DealDTO front, Deal database)  //equals mete subclases etc... Prefiero manual
    {
        return front.getTerms() == database.getTerms()
                && front.getPercentage() == database.getPercentage();
    }
}
