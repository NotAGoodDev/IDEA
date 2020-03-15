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
import ucm.gps.idea.services.DealService;

@RestController
@RequestMapping("/api/deal")
public class DealController {

    @Autowired
    private DealService dealService;

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @PostMapping("/")
    public ResponseEntity<Deal> create(@RequestBody Deal deal){
        dealService.create(deal);
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

}
