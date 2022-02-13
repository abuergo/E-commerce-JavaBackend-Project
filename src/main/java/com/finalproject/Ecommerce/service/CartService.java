package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.document.CartItem;
import com.finalproject.Ecommerce.model.document.Product;
import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.CartRequest;
import com.finalproject.Ecommerce.model.response.CartResponse;

import java.util.List;

public interface CartService {
    public CartResponse createCart(CartRequest cartRequest) throws ApiRestException;
    public List<CartResponse> getAllCarts();
    public CartResponse updateCartItem(Integer orderNumber, CartItem cartItem) throws ApiRestException;
    public void deleteCart(Integer orderNumber) throws ApiRestException;
    public void deleteCartItem(Integer orderNumber, String code) throws ApiRestException;
    public CartResponse addItem(Integer orderNumber, CartItem cartItem) throws ApiRestException;
    public List<CartItem> getAllItems(Integer orderNumber) throws ApiRestException;
    public List<CartItem> getCartItemsByOrderNumber (Integer orderNumber) throws ApiRestException;
    public List<Product> getProductListByOrderNumber(Integer orderNumber) throws ApiRestException;



}
