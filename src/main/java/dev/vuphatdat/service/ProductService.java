package dev.vuphatdat.service;


import dev.vuphatdat.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> findAllProducts();
    void saveProduct(Product product);
}
