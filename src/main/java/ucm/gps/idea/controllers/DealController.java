package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.DealDTO;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.services.DealService;
import ucm.gps.idea.services.IdeaService;

@RestController
@RequestMapping("/api/deal")
public class DealController {

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @Autowired
    private IdeaService ideaService;

    @GetMapping("/{id}")
    public ResponseEntity<DealDTO> dealToFront(@PathVariable Integer id) {  //idIdea
        try {
            Deal deal = dealService.dealByIdeaId(id);
            DealDTO dealDTO = null;

            if(deal == null)    //No ha encontarado un contrato asociado a la idea
            {
                Idea idea = ideaService.index(id);
                 dealDTO= new DealDTO(
                        id,
                        idea.getCreator().getName() + " " + idea.getCreator().getLastName(),
                        idea.getEnterprise().getName(),
                        idea.getTitle(),
                        "", //terms = null
                        0,    //percentage = 0
                        false,  //Creator agree?
                        false   //Enterprise agree?
                );
            }
            else
            {
                dealDTO = new DealDTO(
                        id,
                        deal.getIdea().getCreator().getName() + " " + deal.getIdea().getCreator().getLastName(),
                        deal.getIdea().getEnterprise().getName(),
                        deal.getTitle(),
                        deal.getTerms(),
                        deal.getPercentage(),
                        deal.isCAgree(),
                        deal.isEAgree()
                );
            }

            return new ResponseEntity<>(dealDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(String.format("Read an specific Idea failed id: %i", id));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //Crear idea y gestionarla  ENTERPRISE
    @PostMapping("/enterprise/{id}")   //id de la idea
    public ResponseEntity<DealDTO> manageDealEnterprise(@RequestBody DealDTO dealDTO) throws Exception {
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


    //Crear idea y gestionarla  CREATOR
    @PostMapping("/creator/{id}")
    public ResponseEntity<DealDTO> manageDealCreator(@RequestBody DealDTO dealDTO) throws Exception {
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
        return front.getPercentage() == database.getPercentage()
            && front.getTerms() == database.getTerms();
    }
}
