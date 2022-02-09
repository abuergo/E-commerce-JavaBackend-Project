package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.ProductRequest;
import com.finalproject.Ecommerce.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request) throws ApiRestException;
    ProductResponse update(String code, ProductRequest request) throws ApiRestException;
    List<ProductResponse> getAll();
    ProductResponse getByCode(String code) throws ApiRestException;
    List<ProductResponse> getAllByCategory(String category) throws ApiRestException;
    void delete(String code) throws ApiRestException;
}
