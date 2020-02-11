package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IdeaService {

    private static final Logger logger = LoggerFactory.getLogger(IdeaService.class);

    public String getTest(){
        return "Helo World";
    }

}
