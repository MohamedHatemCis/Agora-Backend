package com.training.agora.category;

import com.training.agora.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByAvailable(boolean available);

    @Modifying
    @Query(value ="UPDATE categories c SET c.available = false WHERE c.cate_id = ?1" ,nativeQuery = true)
    int deleteCategorySoftly(long cate_id);
}
