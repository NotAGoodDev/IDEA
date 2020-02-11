package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucm.gps.idea.services.IdeaService;

@RestController
@RequestMapping("/ideas")
class IdeaController {

    @Autowired
    IdeaService ideaService;

    private static final Logger logger = LoggerFactory.getLogger(IdeaController.class);

    @GetMapping("/test")
    public String test() {
        return ideaService.getTest();
    }

}