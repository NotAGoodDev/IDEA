package ucm.gps.idea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.PackIdea;
import ucm.gps.idea.repositories.PackIdeaRepository;

import java.util.List;

@Service
public class PackIdeaService {

    @Autowired
    PackIdeaRepository packIdeaRepo;

    public List<PackIdea> listAll(){
        return packIdeaRepo.findAll();
    }

    public PackIdea find(Integer id) throws Exception {
        return packIdeaRepo.findById(id).orElseThrow(Exception::new);
    }

    public PackIdea create(PackIdea newPack){
        return packIdeaRepo.save(newPack);
    }

    public void delete(Integer id){
        packIdeaRepo.deleteById(id);
    }

    public PackIdea findByName(String packName) {
        return packIdeaRepo.findByName(packName);
    }
}
