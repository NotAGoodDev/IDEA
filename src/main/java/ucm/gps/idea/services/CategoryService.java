package ucm.gps.idea.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucm.gps.idea.entities.Category;
import ucm.gps.idea.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Category create(Category category) { return categoryRepository.save(category); }

    public Category findByName(String name){return categoryRepository.findByName(name);};

    public Category index(Integer id) throws Exception { return categoryRepository.findById(id).orElseThrow(Exception::new); }

}
