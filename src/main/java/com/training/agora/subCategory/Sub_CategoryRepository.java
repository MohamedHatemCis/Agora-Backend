package com.training.agora.subCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sub_CategoryRepository extends JpaRepository<SubCategory,Long> {
    List<SubCategory>findAllByCategoryId(long categoryId);
}
