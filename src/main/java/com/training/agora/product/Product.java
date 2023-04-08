package com.training.agora.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.training.agora.category.Category;
import com.training.agora.subCategory.SubCategory;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "products")
public class Product {
    @Id
    @SequenceGenerator(sequenceName ="prod_seq" ,name ="prod_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "prod_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "prod_id")
    private long id;
    @Column(name = "prod_name")
    private String name;
    @Column(name = "prod_desc")
    private String description;
    @Column(name = "prod_price")
    private Double price;
    @Column(name = "prod_quantity")
    private Integer quantity;
    @Column(name = "prod_img")
    private String img;
    private Date created_date;
    private Date modified_date;
    private Boolean available;
    @ManyToOne
    @JoinColumn(name = "cate_id")
    @JsonBackReference
    private Category category;
    @ManyToOne
    @JoinColumn(name = "sub_id")
    private SubCategory subCategory;
}
