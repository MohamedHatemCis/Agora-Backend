package com.training.agora.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="api/v1/agora/cart")

// Permit this url to use these end points
@CrossOrigin(origins = "http://localhost:4200")

//Inject the needed classes
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public Boolean saveCart(@RequestBody Cart cart)
    {
        return cartService.saveCart(cart);
    }
    @GetMapping("check/{id}")
    public Boolean checkExistedCartByUserId(@PathVariable long id){
        return cartService.checkExistedCartByUserId(id);
    }
    @GetMapping("{id}")
    public Cart getCartByUserId(@PathVariable long id){
        return cartService.getCartByUserId(id);
    }

}
