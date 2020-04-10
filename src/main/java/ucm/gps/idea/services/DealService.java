package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.repositories.DealRepository;
import ucm.gps.idea.repositories.EnterpriseRepository;
import ucm.gps.idea.repositories.IdeaRepository;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private IdeaRepository ideaRepository;

    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    public List<Deal> list() {
        return dealRepository.findAll();
    }

    public Deal index(Integer id) throws Exception {
        return dealRepository.findById(id).orElseThrow(Exception::new);
    }

    public Deal create(Deal deal) { return dealRepository.save(deal); }

    public void delete(Integer id) {
        dealRepository.deleteById(id);
    }


    public Deal create(Deal deal, Integer enterpriseId, Integer ideaId) throws Exception {

        Date createdDate = new Date();

        deal.setCreatedAt(createdDate);

        Idea idea = ideaRepository.findById(ideaId).orElseThrow(Exception::new);

        Enterprise enterprise = enterpriseRepository.findById(enterpriseId).orElseThrow(Exception::new);

        logger.info(enterprise.getId().toString());

        //  deal.setEnterprise(enterprise);
        deal.setIdea(idea);

        return dealRepository.save(deal);
    }
}
