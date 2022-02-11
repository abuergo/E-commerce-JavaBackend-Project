package com.finalproject.Ecommerce.repository;

import com.finalproject.Ecommerce.model.document.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, Integer> {
    public Cart findByEmail(String email);
    public Cart findByOrderNumber(Integer orderNumber);
}
