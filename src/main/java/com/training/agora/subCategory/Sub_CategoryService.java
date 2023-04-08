package com.training.agora.subCategory;

import com.training.agora.category.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

//Inject the needed classes
@RequiredArgsConstructor
public class Sub_CategoryService {
    private final Sub_CategoryRepository sub_categoryRepository;

    //Save subCategory to a specific category
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

    //Get the subCategories for a specific category and not deleted before from database
    public List<SubCategory>getAllSub_CategoriesWithSpecificCategory(long id){
        return sub_categoryRepository.findAllByCategoryIdAndAvailable(id,true);
    }

    //Delete subCategory from database softly to not affect the mapped entities
    @Transactional
    public int deleteSubCategory(long sub_id)
    {
        return sub_categoryRepository.deleteSubCategorySoftly(sub_id);
    }
}
