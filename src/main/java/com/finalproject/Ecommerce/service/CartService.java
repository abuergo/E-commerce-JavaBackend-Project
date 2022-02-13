package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.document.CartItem;
import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.CartRequest;
import com.finalproject.Ecommerce.model.response.CartItemResponse;
import com.finalproject.Ecommerce.model.response.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse createCart(CartRequest cartRequest) throws ApiRestException;
    List<CartResponse> getAllCarts();
    CartResponse updateCartItem(Integer orderNumber, CartItem cartItem) throws ApiRestException;
    void deleteCart(Integer orderNumber) throws ApiRestException;
    void deleteCartItem(Integer orderNumber, String code) throws ApiRestException;
    CartResponse addItem(Integer orderNumber, CartItem cartItem) throws ApiRestException;
    List<CartItemResponse> getCartItemsByOrderNumber (Integer orderNumber) throws ApiRestException;
}
