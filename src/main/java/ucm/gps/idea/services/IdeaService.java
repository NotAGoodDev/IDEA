package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.repositories.IdeaRepository;

import java.util.List;

@Service
public class IdeaService {

    @Autowired
    IdeaRepository ideaRepository;

    private static final Logger logger = LoggerFactory.getLogger(IdeaService.class);

    public List<Idea> list() {
        return ideaRepository.findAll();
    }

    public Idea index(Integer id) throws Exception {
        return ideaRepository.findById(id).orElseThrow(Exception::new);
    }

    public void delete(Integer id) {
        ideaRepository.deleteById(id);
    }

    public Idea create(Idea idea) {
        return ideaRepository.save(idea);
    }
}
