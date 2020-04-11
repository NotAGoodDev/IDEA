package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.DealDTO;
import ucm.gps.idea.services.DealService;

@RestController
@RequestMapping("/api/enterprise/deal")
public class DealEnterpriseController {

    private static final Logger logger = LoggerFactory.getLogger(DealCreatorController.class);

    @Autowired
    private DealService dealService;

    @PostMapping("/{id}")   //id de la idea
    public ResponseEntity<DealDTO> manageDeal(@RequestBody DealDTO dealDTO) throws Exception {
        try {
            if(dealDTO.getId() == null)     //Si no existe el contrato
                dealService.create(dealDTO);    //Lo creamos

            else if(!dealService.index(dealDTO.getId()).isCAgree()
                    || !dealService.index(dealDTO.getId()).isEAgree())  //Si estan o no de acuerdo las dos partes
            {
                if (!equals(dealDTO, dealService.index(dealDTO.getId())))  //Si se han cambiado las condiciones (contraoferta)
                    dealService.index(dealDTO.getId()).setCAgree(false);    //El creador no la ha aceptado

                //la empresa esta de acuerdo porque ha hecho la oferta
                dealService.index(dealDTO.getId()).setEAgree(true);
            }

            return new ResponseEntity<>(dealDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    boolean equals(DealDTO front, Deal database)  //equals mete subclases etc... Prefiero manual
    {
        return front.getPercentage() == database.getPercentage()
            && front.getTerms() == database.getTerms();
    }
}
