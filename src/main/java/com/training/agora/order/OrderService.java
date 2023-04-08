package com.training.agora.order;

import com.training.agora.cart.CartService;
import com.training.agora.cart_item.Cart_ItemRepository;
import com.training.agora.cart_item.Cart_ItemService;
import com.training.agora.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

//Inject the needed classes
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final Cart_ItemService cartItemService;
    private  final ProductService productService;

    //Save order
    public Boolean saveOrder(Order order) {
        try {
            //Save the order in database
           Order savedOrder= orderRepository.save(order);
           // Update each product quantity after order
           productService.updateEachProductAfterOrder(order.getUser().getId());
           //Update each cart item as ordered
           cartItemService.assignItemsAsOrdered(order.getUser().getId(),savedOrder.getId());
           //Reset total and number of items
           cartService.clearCartData(order.getUser().getId());
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    //Get order details by the id
    public Order getOrderById(long id){
        return orderRepository.findById(id).orElseThrow();
    }

    //Get all user orders
    public List<Order> getUserOrders(long user_id){
        return orderRepository.getAllByUserId(user_id);
    }

    //Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
