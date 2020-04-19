package ucm.gps.idea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.repositories.EnterpriseRepository;
import ucm.gps.idea.repositories.IdeaRepository;

import java.util.List;

@Service
public class IdeaService {

    @Autowired
    IdeaRepository ideaRepository;

    @Autowired
    EnterpriseRepository enterpriseRepository;

    public List<Idea> list() {
        return ideaRepository.findAll();
    }

    public List<Idea> listByDate() {
        return ideaRepository.findByOrderByCreatedAtDesc();
    }

    public List<Idea> listByState(String state) {
        return ideaRepository.findByState(state);
    }

    public Idea index(Integer id) throws Exception {
        return ideaRepository.findById(id).orElseThrow(Exception::new);
    }

    public void delete(Integer id) {
        ideaRepository.deleteById(id);
    }

    public Idea save(Idea idea) {
        return ideaRepository.save(idea);
    }

    public Idea send(Integer ideaID, Integer enterpriseID) throws Exception {
        Idea idea = ideaRepository.findById(ideaID).orElseThrow(Exception::new);
        Enterprise enterprise = enterpriseRepository.findById(enterpriseID).orElseThrow(Exception::new);
        idea.setEnterprise(enterprise);
        return ideaRepository.save(idea);

    }
}
