package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Deal;
import ucm.gps.idea.repositories.DealRepository;

import java.util.List;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

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


}
