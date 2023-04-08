package com.training.agora.subCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sub_CategoryRepository extends JpaRepository<SubCategory,Long> {
    List<SubCategory>findAllByCategoryIdAndAvailable(long categoryId,Boolean available);
    @Modifying
    @Query(value ="UPDATE sub_categories s SET s.available = false WHERE s.sub_id = ?1" ,nativeQuery = true)
    int deleteSubCategorySoftly(long sub_id);
}
