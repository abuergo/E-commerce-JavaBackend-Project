package com.finalproject.Ecommerce.builder;

import com.finalproject.Ecommerce.model.document.Cart;
import com.finalproject.Ecommerce.model.request.CartRequest;
import com.finalproject.Ecommerce.model.response.CartResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartBuilder {
    public static Cart requestToDocument(CartRequest cartRequest){
        return Cart.builder()
                .email(cartRequest.getEmail())
                .products(new ArrayList<>())
                .deliveryAddress(cartRequest.getDeliveryAddress())
                .orderNumber((int) Math.floor(Math.random()))
                .status("Generated")
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static CartResponse documentToResponse(Cart cartDocument){
        return CartResponse.builder()
                .orderNumber(cartDocument.getOrderNumber())
                .products(cartDocument.getProducts())
                .email(cartDocument.getEmail())
                .status(cartDocument.getStatus())
                .creationDate(cartDocument.getCreationDate())
                .build();
    }

    public static List<CartResponse> documentListToResponse(List<Cart> cartDocumentList){
        List<CartResponse> list = new ArrayList<>();
        cartDocumentList.forEach(cart -> list.add(documentToResponse(cart)));
        return list;
    }
}
