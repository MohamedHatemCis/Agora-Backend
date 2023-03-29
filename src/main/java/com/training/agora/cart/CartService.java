package com.training.agora.cart;

import com.training.agora.user.User;
import com.training.agora.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    public Boolean saveCart(Cart cart){
        try {
            cartRepository.save(cart);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    public Cart getCartByUserId(long id)
    {
        Boolean found=checkExistedCartByUserId(id);
        if(found)
             return cartRepository.getByUserId(id).orElseThrow();
        else {
            User user=userService.getUserByUserId(id).orElseThrow();
            Cart newCart=new Cart();
            newCart.setUser(user);
            saveCart(newCart);
            return newCart;
        }
    }
    public Boolean checkExistedCartByUserId(long id)
    {
        Optional<Cart> cart = cartRepository.getByUserId(id);
        if(cart.isPresent())
            return true;
        else {
           return false;
        }
    }

    @Transactional
    public void updateCartData(long cart_id,double price,int num_of_items){
        Cart cart=cartRepository.findById(cart_id).orElseThrow();
        cart.setTotal(cart.getTotal()+price);
        cart.setNum_of_items(cart.getNum_of_items()+num_of_items);
    }
    @Transactional
    public void clearCartData(long user_id){
        Cart cart=cartRepository.getCartByUserId(user_id);
        cart.setTotal(0.0);
        cart.setNum_of_items(0);
    }
}
