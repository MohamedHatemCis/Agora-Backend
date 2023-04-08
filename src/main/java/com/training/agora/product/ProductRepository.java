package com.training.agora.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategoryIdAndSubCategoryIdAndAvailable(long cate_id,long sub_id,boolean available);

    @Modifying
    @Query(value ="UPDATE products p SET p.prod_quantity = p.prod_quantity-?2 WHERE p.prod_id = ?1" ,nativeQuery = true)
    int updateQuantityForEachProduct(long prod_id,int decreasedQuantity);

    @Modifying
    @Query(value ="UPDATE products p SET p.available = false WHERE p.prod_id = ?1" ,nativeQuery = true)
    int updateProductAvailability(long prod_id);
}
