package com.finalproject.Ecommerce.service.implementation;

import com.finalproject.Ecommerce.builder.ProductBuilder;
import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.ProductRequest;
import com.finalproject.Ecommerce.model.response.ProductResponse;
import com.finalproject.Ecommerce.repository.ProductRepository;
import com.finalproject.Ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) throws ApiRestException{
        validateCreateRequest(request);
        var document = productRepository.save(ProductBuilder.requestToDocument(request));
        return ProductBuilder.documentToResponseCreate(document);
    }

    @Override
    public ProductResponse update(String code, ProductRequest request) throws ApiRestException{
        validateExistenceRequest(code);
        var product = productRepository.findByCode(request.getCode());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setCode(request.getCode());
        product.setModificationDate(LocalDateTime.now());
        var productUpdated = productRepository.save(product);
        return ProductBuilder.documentToResponseUpdate(productUpdated);
    }

    @Override
    public List<ProductResponse> getAll() {
        return ProductBuilder.documentListToResponseGetAll(productRepository.findAll());
    }

    @Override
    public ProductResponse getByCode(String code) throws ApiRestException{
        validateExistenceRequest(code);
        var productFound = productRepository.findByCode(code);
        return ProductBuilder.documentToResponse(productFound);
    }

    @Override
    public List<ProductResponse> getAllByCategory(String category) throws ApiRestException {
        var productListByCategory = productRepository.findAllByCategory(category);
        if (Objects.isNull(productListByCategory)) {
            throw new ApiRestException(category, "There are no products with that category");
        }
        return ProductBuilder.documentListToResponseGetAll(productListByCategory);
    }

    @Override
    public void delete(String code) throws ApiRestException {
        validateExistenceRequest(code);
        var productToDelete = productRepository.findByCode(code);
        productRepository.delete(productToDelete);
    }

    private void validateCreateRequest(ProductRequest request) throws ApiRestException {
        var product = productRepository.findByCode(request.getCode());
        if(!Objects.isNull(product)){
            throw new ApiRestException("The product already exists");
        }
    }

    private void validateExistenceRequest(String code) throws ApiRestException{
        var product = productRepository.findByCode(code);
        if(Objects.isNull(product)){
            throw new ApiRestException(code, "The product does not exist");
        }
    }
}
