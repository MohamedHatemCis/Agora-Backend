package com.training.agora.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

//Inject the needed classes
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //Save Category to the database
    public Boolean saveCategory(Category category){
       try {
           categoryRepository.save(category);
           return true;
       }catch (Exception ex){
           return false;
       }
    }

    //Get all categories from database
    public List<Category>getAllCategories(){
        return categoryRepository.findAllByAvailable(true);
    }

    //Delete category from database softly to not affect the mapped entities
    @Transactional
    public int deleteCategory(long cate_id)
    {
         return categoryRepository.deleteCategorySoftly(cate_id);
    }
}
