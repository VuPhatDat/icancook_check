package dev.vuphatdat.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

    @Column(name = "product_category_name")
    private String name;


    @OneToMany(mappedBy = "productCategory")
    private Set<Product> product;
}
