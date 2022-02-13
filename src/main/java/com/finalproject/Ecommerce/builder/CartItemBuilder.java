package com.finalproject.Ecommerce.builder;

import com.finalproject.Ecommerce.model.document.CartItem;
import com.finalproject.Ecommerce.model.document.Product;
import com.finalproject.Ecommerce.model.response.CartItemResponse;

public class CartItemBuilder {

    public static CartItemResponse cartItemAndProductToDocument(CartItem cartItem, Product product){
        return CartItemResponse.builder()
                .category(product.getCategory())
                .code(product.getCode())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
