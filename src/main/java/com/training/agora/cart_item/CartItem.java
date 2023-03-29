package com.training.agora.cart_item;

import com.training.agora.cart.Cart;
import com.training.agora.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class CartItem {
    @Id
    @SequenceGenerator(sequenceName ="item_seq" ,name ="item_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "item_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private long id;
    private Integer prod_quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    private long order_id;

    private Long user_id;

}
