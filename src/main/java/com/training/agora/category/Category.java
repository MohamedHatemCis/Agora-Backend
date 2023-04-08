package com.training.agora.category;

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
@Entity(name = "categories")
public class Category {
    @Id
    @SequenceGenerator(sequenceName ="cate_seq" ,name ="cate_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "cate_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "cate_id")
    private long id;
    @Column(name = "cate_name")
    private String name;

    private Boolean available;

//    @OneToMany
//    @JsonBackReference
//    private List<SubCategory> subCategories;
}
