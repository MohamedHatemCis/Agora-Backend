package com.training.agora.order;

import com.training.agora.cart.CartService;
import com.training.agora.cart_item.Cart_ItemRepository;
import com.training.agora.cart_item.Cart_ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final Cart_ItemService cartItemService;

    public Boolean saveOrder(Order order) {
        try {
           Order savedOrder= orderRepository.save(order);
           cartItemService.assignItemsAsOrdered(order.getUser().getId(),savedOrder.getId());
           cartService.clearCartData(order.getUser().getId());
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    public Order getOrderById(long id){
        return orderRepository.findById(id).orElseThrow();
    }
    public List<Order> getUserOrders(long user_id){
        return orderRepository.getAllByUserId(user_id);
    }
}
