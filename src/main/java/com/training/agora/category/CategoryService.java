package com.training.agora.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Boolean saveCategory(Category category){
       try {
           categoryRepository.save(category);
           return true;
       }catch (Exception ex){
           return false;
       }
    }

    public List<Category>getAllCategories(){
        return categoryRepository.findAll();
    }
}
