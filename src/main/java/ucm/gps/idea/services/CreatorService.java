package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.repositories.CreatorRepository;

import java.util.List;

@Service
public class CreatorService {

    @Autowired
    CreatorRepository creatorRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreatorService.class);

    public List<Creator> list() {
        return creatorRepository.findAll();
    }

    public Creator index(Integer id) throws Exception {
        return creatorRepository.findById(id).orElseThrow(Exception::new);
    }

    public Creator create(Creator creator) { return creatorRepository.save(creator); }

    public void delete(Integer id) {
        creatorRepository.deleteById(id);
    }

    public Creator findByUsername(String username){
        return creatorRepository.findByUsername(username);
    }

}
