package com.training.agora.order;

import com.training.agora.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(sequenceName ="order_seq" ,name ="order_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "order_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private long id;

    private Double total;
    private int num_of_items;
    private Date created_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
