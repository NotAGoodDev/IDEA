package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Creator;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.repositories.CreatorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatorService implements UserDetailsService {

    @Autowired
    CreatorRepository creatorRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreatorService.class);

    public List<Creator> list() {
        return creatorRepository.findAll();
    }

    public Creator index(Integer id) throws Exception {
        return creatorRepository.findById(id).orElseThrow(Exception::new);
    }

    public Creator create(Creator creator) { return creatorRepository.save(creator); }

    public void delete(Integer id) {
        creatorRepository.deleteById(id);
    }

    public Creator findByUsername(String username){
        return creatorRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Creator ret = creatorRepository.findByUsername(username);

        // Podemos meterlo en otra capa para coger los roles de la base de datos y no hacerlo aqui a mano
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails details = new User(ret.getUsername(), ret.getPassword(), roles);

        return details;
    }
}
