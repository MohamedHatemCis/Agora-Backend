package com.training.agora.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.training.agora.cart_item.CartItem;
import com.training.agora.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Table
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {
    @Id
    @SequenceGenerator(sequenceName ="cart_seq" ,name ="cart_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "cart_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_id")
    private long id;

    private Double total;

    private int num_of_items;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @Transient
    @JsonBackReference
    private List<CartItem>items;

    public Cart() {
        total=0.0;
        num_of_items=0;
        items=new ArrayList<>();
    }
}
