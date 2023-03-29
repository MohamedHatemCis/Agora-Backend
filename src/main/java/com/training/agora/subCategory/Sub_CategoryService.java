package com.training.agora.subCategory;

import com.training.agora.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Sub_CategoryService {
    private final Sub_CategoryRepository sub_categoryRepository;

    public Boolean saveSub_Category(SubCategory subCategory){
       try {
           sub_categoryRepository.save(subCategory);
       }catch (Exception ex){
           return false;
       }
       finally {
           return true;
       }
    }

    public List<SubCategory>getAllSub_Categories(){
        return sub_categoryRepository.findAll();
    }

    public List<SubCategory>getAllSub_CategoriesWithSpecificCategory(long id){
        return sub_categoryRepository.findAllByCategoryId(id);
    }
}
