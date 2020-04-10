package ucm.gps.idea.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Admin;
import ucm.gps.idea.entities.Enterprise;
import ucm.gps.idea.repositories.AdminRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);


}
