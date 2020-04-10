package ucm.gps.idea.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ucm.gps.idea.entities.Category;
import ucm.gps.idea.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("api/categories")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/")
    public ResponseEntity<List<Category>> list(){
        List<Category> listCategories = categoryService.list();
        return new ResponseEntity<>(listCategories, HttpStatus.OK);
    }
}
