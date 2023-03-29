package com.training.agora.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/agora/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public Boolean saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }
    @GetMapping(path = "user/{id}")
    public List<Order> getAllUserOrders(@PathVariable long id){
        return orderService.getUserOrders(id);
    }
    @GetMapping(path = "{id}")
    public Order getOrderById(@PathVariable long id){
       return orderService.getOrderById(id);
    }
}
