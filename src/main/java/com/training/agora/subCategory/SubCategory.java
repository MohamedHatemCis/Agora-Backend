package com.training.agora.subCategory;

import com.training.agora.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "sub-categories")
public class SubCategory {
    @Id
    @SequenceGenerator(sequenceName ="sub_seq" ,name ="sub_seq" ,allocationSize = 1)
    @GeneratedValue(generator = "sub_seq",strategy = GenerationType.SEQUENCE)
    @Column(name = "sub_id")
    private long id;
    @Column(name = "sub_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;
}
