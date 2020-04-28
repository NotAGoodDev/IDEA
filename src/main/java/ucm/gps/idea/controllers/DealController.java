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
import ucm.gps.idea.repositories.DealRepository;
import ucm.gps.idea.services.DealService;
import ucm.gps.idea.services.IdeaService;

import java.util.Date;

@RestController
@RequestMapping("/api/deal")
public class DealController {

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @Autowired
    private IdeaService ideaService;

    @Autowired
    private DealRepository dealRepository;

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
                        deal.getIdea().getTitle(),
                        deal.getTerms(),
                        deal.getPercentage(),
                        deal.isCAgree(),
                        deal.isEAgree()
                );
            }

            return new ResponseEntity<>(dealDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Read an specific Idea failed id: " + id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //Crear idea y gestionarla  ENTERPRISE
    @PostMapping("/enterprise")   //id de la idea
    public ResponseEntity<DealDTO> manageDealEnterprise(@RequestBody DealDTO dealDTO) throws Exception {
        try {

            Deal deal = dealService.dealByIdeaId(dealDTO.getIdeaId());

            if(deal == null)     //Si no existe el contrato
                dealService.create(dealDTO);    //Lo creamos
            else
                dealService.manageDealEnterprise(dealDTO, deal);

            return new ResponseEntity<>(dealDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //Crear idea y gestionarla  CREATOR
    @PostMapping("/creator")
    public ResponseEntity<DealDTO> manageDealCreator(@RequestBody DealDTO dealDTO) throws Exception {
        try {
            Deal deal = dealService.dealByIdeaId(dealDTO.getIdeaId());

            if(deal == null)     //Si no existe el contrato
                dealService.create(dealDTO);    //Lo creamos
            else
                dealService.manageDealCreator(dealDTO, deal);

            return new ResponseEntity<>(dealDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
