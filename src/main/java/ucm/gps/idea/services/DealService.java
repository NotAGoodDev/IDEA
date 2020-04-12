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

        Date createdDate = new Date();

        logger.info(createdDate.toString());

        deal.setCreatedAt(createdDate);

        Idea idea = ideaRepository.findById(dealDTO.getIdeaId()).orElseThrow(Exception::new);

        logger.info(idea.getId().toString());

        Enterprise enterprise = idea.getEnterprise();

        logger.info(enterprise.getId().toString());

        //  deal.setEnterprise(enterprise);
        deal.setIdea(idea);
        idea.setDeal(deal);
        return dealRepository.save(deal);
    }


    public void manageDealEnterprise(DealDTO dealDTO, Deal deal)
    {
        if(!deal.isCAgree()
                || !deal.isEAgree())  //Si estan o no de acuerdo las dos partes
        {
            if (!equals(dealDTO, deal))  //Si se han cambiado las condiciones (contraoferta)
                update(dealDTO, deal);

            //la empresa esta de acuerdo porque ha hecho la oferta
            deal.setEAgree(true);
        }
        else    //Los dos han firmado
            deal.setDateSigned(new Date());

        dealRepository.save(deal);
    }


    public void manageDealCreator(DealDTO dealDTO, Deal deal)
    {
        if(!deal.isCAgree()
                || !deal.isEAgree())  //Si estan o no de acuerdo las dos partes
        {
            if (!equals(dealDTO, deal))  //Si se han cambiado las condiciones (contraoferta)
                update(dealDTO, deal);

            //El creador esta de acuerdo porque ha hecho la oferta
            deal.setCAgree(true);
        }
        else    //Los dos han firmado
            deal.setDateSigned(new Date());

        dealRepository.save(deal);
    }


    public void update(DealDTO dealDTO, Deal deal)
    {
        deal.setCAgree(false);    //El creador no la ha aceptado
        deal.setTerms(dealDTO.getTerms());
        deal.setPercentage(dealDTO.getPercentage());
    }


    public boolean equals(DealDTO front, Deal database)  //equals mete subclases etc... Prefiero manual
    {
        return front.getPercentage() == database.getPercentage()
                && front.getTerms().equals(database.getTerms());
    }
}
