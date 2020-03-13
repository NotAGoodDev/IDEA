package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.repositories.EnterpriseRepository;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    EnterpriseRepository enterpriseRepository;

    private static final Logger logger = LoggerFactory.getLogger(EnterpriseService.class);

    public List<Enterprise> list() {
        return enterpriseRepository.findAll();
    }

    public Enterprise index(Integer id) throws Exception {
        return enterpriseRepository.findById(id).orElseThrow(Exception::new);
    }

    public void delete(Integer id) {
        enterpriseRepository.deleteById(id);
    }

    public Enterprise create(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public Enterprise findByCif(String cif){
        return enterpriseRepository.findByCIF(cif);
    }
}
