package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.entities.DealDTO;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.entities.Idea;
import ucm.gps.idea.repositories.DealRepository;
import ucm.gps.idea.repositories.EnterpriseRepository;
import ucm.gps.idea.repositories.IdeaRepository;


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

    @Autowired
    private IdeaService ideaService;

    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    public List<Deal> list() {
        return dealRepository.findAll();
    }

    public Deal index(Integer id) throws Exception {
        return dealRepository.findById(id).orElseThrow(Exception::new);
    }

    public Deal dealByIdeaId(Integer idIdea) throws Exception {
        try {
            Idea idea = ideaRepository.findById(idIdea).orElseThrow(Exception::new);
            return dealRepository.findById(idea.getDeal().getId()).orElseThrow(Exception::new);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Deal create(Deal deal) {
        return dealRepository.save(deal);
    }

    public void delete(Integer id) {
        dealRepository.deleteById(id);
    }

    public Deal create(DealDTO dealDTO) throws Exception {

        Deal deal = new Deal();
        deal.setPercentage(dealDTO.getPercentage());
        deal.setTerms(dealDTO.getTerms());
        deal.setTitle(dealDTO.getTitle());

        Date createdDate = new Date();

        logger.info(createdDate.toString());

        deal.setCreatedAt(createdDate);

        Idea idea = ideaRepository.findById(dealDTO.getIdeaId()).orElseThrow(Exception::new);

        logger.info(idea.getId().toString());

        Enterprise enterprise = enterpriseRepository.findById(
                enterpriseRepository.findByName(
                        dealDTO.getEnterprise()).getId()).orElseThrow(Exception::new);

        logger.info(enterprise.getId().toString());

        //  deal.setEnterprise(enterprise);
        deal.setIdea(idea);

        return dealRepository.save(deal);
    }
}
