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
@RequiredArgsConstructor
public class Cart_ItemService {
    private final Cart_ItemRepository cartItemRepository;
    private final CartService cartService;
    public Boolean saveCartItem(CartItem cartItem){
        try {
            Cart cart=cartService.getCartByUserId(cartItem.getUser_id());
            cartService.updateCartData(cart.getId(),(cartItem.getProd_quantity()*cartItem.getProduct().getPrice()),cartItem.getProd_quantity());
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public List<CartItem> getAllUserCartItems(long id){
        return cartItemRepository.findAllCartItems(id);
    }

    public CartItem getCartItem(long user_id , long cart_id , long prod_id){
        return cartItemRepository.getCartItem(user_id,cart_id,prod_id);
    }
    @Transactional
    public void assignItemsAsOrdered(Long user_id,Long order_id) {
        cartItemRepository.assignItemsAsOrdered(user_id,order_id);
    }

    @Transactional
    public int deleteItemFromCart(long user_id,long cart_id,long prod_id){
        CartItem cartItem=getCartItem(user_id,cart_id,prod_id);
        cartService.updateCartData(cart_id,((cartItem.getProduct().getPrice()*cartItem.getProd_quantity())*-1),(cartItem.getProd_quantity()*-1));
       return cartItemRepository.deleteItemFromCart(user_id,prod_id);
    }

    public List<CartItem> getAllUserOrderItems(long userId, long orderId) {
        return cartItemRepository.getAllOrderItems(userId,orderId);
    }
    @Transactional
    public int clearItemsFromCart(long userId) {
        cartService.clearCartData(userId);
        return cartItemRepository.clearAllCartItems(userId);
    }

    public Boolean checkInCartItem(long userId, long prodId) {
        List<CartItem> items=cartItemRepository.checkItemFromCart(userId,prodId);
        return items.size()>0?true:false;
    }
}
