package com.finalproject.Ecommerce.controller;

import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.ProductRequest;
import com.finalproject.Ecommerce.model.response.ProductResponse;
import com.finalproject.Ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.getAll();
    }

    @PostMapping
    public ProductResponse createProduct(@Validated @RequestBody ProductRequest request) throws ApiRestException {
        return productService.create(request);
    }

    @PutMapping("/{code}")
    public ProductResponse updateProduct(@PathVariable String code, @Validated @RequestBody ProductRequest request) throws ApiRestException {
        return productService.update(code, request);
    }

    @DeleteMapping("/{code}")
    public void deleteProduct(@PathVariable String code) throws ApiRestException {
        productService.delete(code);
    }

    @GetMapping("/{code}")
    public ProductResponse getProductByCode(@PathVariable String code) throws ApiRestException{
        return productService.getByCode(code);
    }

    @GetMapping("/")
    public List<ProductResponse> getAllProductsByCategory(@RequestParam String category) throws ApiRestException{
        return productService.getAllByCategory(category);
    }

}
