package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.DealDTO;
import ucm.gps.idea.services.DealService;

@RestController
@RequestMapping("api/deal")
public class DealController {

    @Autowired
    private DealService dealService;

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @PostMapping("/")
    public ResponseEntity<DealDTO> create(@RequestBody DealDTO dealDTO) throws Exception {

        try {
            Deal deal = new Deal();
            deal.setPercentage(dealDTO.getPercentage());
            deal.setText(dealDTO.getText());
            deal.setTitle(dealDTO.getTitle());
            dealService.create(deal, dealDTO.getEnterprise_id(), dealDTO.getIdea_id());
            return new ResponseEntity<>(dealDTO, HttpStatus.OK);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
