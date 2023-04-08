package com.training.agora.cart_item;

import com.training.agora.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Cart_ItemRepository extends JpaRepository<CartItem,Long> {
    @Query(value = "select * from cart_item where user_id=?1 and order_id = 0",nativeQuery = true)
    List<CartItem> findAllCartItems(Long id);

    @Query(value = "select * from cart_item where order_id = ?1",nativeQuery = true)
    List<CartItem> getAllOrderItems(Long order_id);

    @Query(value = "select * from cart_item where user_id=?1 and prod_id=?2 and order_id = 0",nativeQuery = true)
    List<CartItem> checkItemFromCart(Long user_id, Long prod_id);

    @Query(value = "select * from cart_item where user_id=?1 and cart_id = ?2 and prod_id = ?3 and order_id = 0",nativeQuery = true)
    CartItem getCartItem(Long user_id, Long cart_id , Long prod_id);
    @Modifying
    @Query(value ="UPDATE cart_item SET  order_id = :order_id WHERE  user_id = :user_id AND order_id = 0" ,nativeQuery = true)
    void assignItemsAsOrdered(@Param("user_id") Long user_id, @Param("order_id") Long order_id);

    @Modifying
    @Query(value ="DELETE FROM cart_item WHERE user_id = :user_id AND prod_id = :prod_id AND order_id = 0" ,nativeQuery = true)
    int deleteItemFromCart(@Param("user_id") Long user_id, @Param("prod_id") Long prod_id);

    @Modifying
    @Query(value ="DELETE FROM cart_item WHERE user_id = :user_id AND order_id = 0" ,nativeQuery = true)
    int clearAllCartItems(@Param("user_id") Long user_id);
}
