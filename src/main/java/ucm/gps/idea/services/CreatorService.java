package ucm.gps.idea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.repositories.CreatorRepository;

@Service
public class CreatorService {

    @Autowired
    CreatorRepository creatorRepository;

    public Creator create(Creator creator) { return creatorRepository.save(creator); }

    //pendiente de picar
}
