package com.finalproject.Ecommerce.repository;

import com.finalproject.Ecommerce.model.document.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, Integer> {
    Cart findByEmail(String email);
    Cart findByOrderNumber(Integer orderNumber);
}
