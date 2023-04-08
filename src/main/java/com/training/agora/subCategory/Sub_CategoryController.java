package com.training.agora.subCategory;

import com.training.agora.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/agora/sub_category")

//Inject the needed classes
@RequiredArgsConstructor

// Permit this url to use these end points
@CrossOrigin(origins = "http://localhost:4200")

public class Sub_CategoryController {
    private final Sub_CategoryService subCategoryService;

    @PostMapping
    public Boolean saveSub_Category(@RequestBody SubCategory subCategory)
    {
       return subCategoryService.saveSub_Category(subCategory);
    }


    @GetMapping("/{id}")
    public List<SubCategory> getAllSub_CategoriesWithCategoryId(@PathVariable long id)
    {
        return subCategoryService.getAllSub_CategoriesWithSpecificCategory(id);
    }

    @DeleteMapping(path = "/{id}")
    public int deleteSubCategory(@PathVariable long id){
       return subCategoryService.deleteSubCategory(id);
    }
}
