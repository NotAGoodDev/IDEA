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


    public Deal create(Deal deal, Integer enterprise_id, Integer idea_id) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Date created_date = new Date(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());

        logger.info(created_date.toString());

        deal.setCreatedAt(created_date);

        Idea idea = ideaRepository.findById(idea_id).orElseThrow(Exception::new);

        logger.info(idea.getId().toString());

        Enterprise enterprise = enterpriseRepository.findById(enterprise_id).orElseThrow(Exception::new);

        logger.info(enterprise.getId().toString());



      //  deal.setEnterprise(enterprise);
        deal.setIdea(idea);

        return dealRepository.save(deal);
    }
}
