package com.finalproject.Ecommerce.repository;

import com.finalproject.Ecommerce.model.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByCode(String code);
    List<Product> findAllByCategory(String category);
}
