package dev.vuphatdat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Locale;
import java.util.Set;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_sku")
    private String sku;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private Float price;

    @Column(name = "product_weight")
    private Float weight;

    @Lob
    @Column(name = "product_image", columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "product_update_date")
    private Date updateDate;

    @Column(name="product_stock")
    private Float stock;

    @Column(name = "product_location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "product_category_Id", referencedColumnName = "product_category_id", nullable = false)
    private ProductCategory productCategory;


}
