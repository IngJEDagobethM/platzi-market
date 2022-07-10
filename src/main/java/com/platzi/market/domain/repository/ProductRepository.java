package com.platzi.market.domain.repository;

import com.platzi.market.domain.dto.Product;

import java.util.List;
import java.util.Optional;
// Toma los métodos implementados son los orientados al dominio
public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(Long categoryId);
    Optional<List<Product>> getScarseProducts(Long quantity);
    Optional<Product> getProduct(Long productID);
    Product save(Product product);
    void delete(Long productId);
}
