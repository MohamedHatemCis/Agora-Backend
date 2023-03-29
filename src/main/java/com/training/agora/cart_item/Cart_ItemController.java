package com.training.agora.cart_item;

import com.training.agora.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/agora/cart-item")
@CrossOrigin("*")
@RequiredArgsConstructor
public class Cart_ItemController {
    private final Cart_ItemService cartItemService;

    @PostMapping
    public Boolean saveCartItem(@RequestBody CartItem cartItem)
    {
        return cartItemService.saveCartItem(cartItem);
    }
    @GetMapping(path = "{id}")
    public List<CartItem> getCartItems(@PathVariable long id)
    {
        return cartItemService.getAllUserCartItems(id);
    }
    @GetMapping(path = "user/{user_id}/product/{prod_id}")
    public Boolean checkInCartItem(@PathVariable long user_id,@PathVariable long prod_id)
    {
        return cartItemService.checkInCartItem(user_id,prod_id);
    }
    @GetMapping(path = "user/{user_id}/order/{order_id}")
    public List<CartItem> getOrderItems(@PathVariable long user_id ,@PathVariable long order_id  )
    {
        return cartItemService.getAllUserOrderItems(user_id,order_id);
    }

    @DeleteMapping(path = "user/{user_id}/cart/{cart_id}/product/{prod_id}")
    public int deleteItemFromCart(@PathVariable long user_id ,@PathVariable long cart_id,@PathVariable long prod_id  )
    {
        return cartItemService.deleteItemFromCart(user_id,cart_id,prod_id);
    }

    @DeleteMapping(path = "user/{user_id}/clear")
    public int clearCart(@PathVariable long user_id)
    {
        return cartItemService.clearItemsFromCart(user_id);
    }

}
