package com.finalproject.Ecommerce.builder;

import com.finalproject.Ecommerce.model.document.Product;
import com.finalproject.Ecommerce.model.request.ProductRequest;
import com.finalproject.Ecommerce.model.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {
    public static Product requestToDocument(ProductRequest request) {
        return Product.builder()
                .code(request.getCode())
                .category(request.getCategory())
                .price(request.getPrice())
                .description(request.getDescription())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static ProductResponse documentToResponseCreate(Product product) {
        return ProductResponse.builder()
                .code(product.getCode())
                .creationDate(product.getCreationDate())
                .modificationDate(product.getModificationDate())
                .build();
    }

    public static ProductResponse documentToResponseUpdate(Product product){
        return ProductResponse.builder()
                .code(product.getCode())
                .creationDate(product.getCreationDate())
                .modificationDate(product.getModificationDate())
                .build();
    }

    public static ProductResponse documentToResponse(Product product){
        return ProductResponse.builder()
                .code(product.getCode())
                .price(product.getPrice())
                .category(product.getCategory())
                .description(product.getDescription())
                .creationDate(product.getCreationDate())
                .modificationDate(product.getModificationDate())
                .build();
    }

    public static List<ProductResponse> documentListToResponseGetAll(List<Product> documentList){
        var productResponseList = new ArrayList<ProductResponse>();
        documentList.forEach(product -> productResponseList.add(documentToResponse(product)));
        return productResponseList;
    }



}
