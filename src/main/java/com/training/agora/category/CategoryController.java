package com.training.agora.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/agora/category")

//Inject the needed classes
@RequiredArgsConstructor

// Permit this url to use these end points
@CrossOrigin(origins = "http://localhost:4200")

public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public Boolean saveCategory(@RequestBody Category category)
    {
       return categoryService.saveCategory(category);
    }
    @GetMapping
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }
    @DeleteMapping(path = "/{id}")
    public int deleteCategory(@PathVariable long id){
       return categoryService.deleteCategory(id);
    }
}
