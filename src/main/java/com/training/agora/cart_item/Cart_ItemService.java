package com.training.agora.cart_item;

import com.training.agora.cart.Cart;
import com.training.agora.cart.CartService;
import com.training.agora.user.User;
import com.training.agora.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

//Inject the needed classes
@RequiredArgsConstructor
public class Cart_ItemService {
    private final Cart_ItemRepository cartItemRepository;
    private final CartService cartService;

    //Save cart item
    public Boolean saveCartItem(CartItem cartItem){
        try {
            //Get the cart by userId
            Cart cart=cartService.getCartByUserId(cartItem.getUser_id());
            // Update cart data , total , number of items
            cartService.updateCartData(cart.getId(),(cartItem.getProd_quantity()*cartItem.getProduct().getPrice()),cartItem.getProd_quantity());
            cartItem.setCart(cart);
            //TODO save the new item
            cartItemRepository.save(cartItem);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }


    //Get all cart items by cart_id
    public List<CartItem> getAllUserCartItems(long id){
        return cartItemRepository.findAllCartItems(id);
    }

    //Get specific cart item
    public CartItem getCartItem(long user_id , long cart_id , long prod_id){
        return cartItemRepository.getCartItem(user_id,cart_id,prod_id);
    }

    //Make Item as ordered by setting the order id from zero to the new order id
    @Transactional
    public void assignItemsAsOrdered(Long user_id,Long order_id) {
        cartItemRepository.assignItemsAsOrdered(user_id,order_id);
    }

    //Delete item from cart
    @Transactional
    public int deleteItemFromCart(long user_id,long cart_id,long prod_id){
        CartItem cartItem=getCartItem(user_id,cart_id,prod_id);
        cartService.updateCartData(cart_id,((cartItem.getProduct().getPrice()*cartItem.getProd_quantity())*-1),(cartItem.getProd_quantity()*-1));
       return cartItemRepository.deleteItemFromCart(user_id,prod_id);
    }

    //Get all Order items
    public List<CartItem> getAllUserOrderItems(long orderId) {
        return cartItemRepository.getAllOrderItems(orderId);
    }


    //Delete all items in the cart
    @Transactional
    public int clearItemsFromCart(long userId) {
        cartService.clearCartData(userId);
        return cartItemRepository.clearAllCartItems(userId);
    }

    //Check if the item is in cart or not
    public Boolean checkInCartItem(long userId, long prodId) {
        List<CartItem> items=cartItemRepository.checkItemFromCart(userId,prodId);
        return items.size()>0?true:false;
    }
}
