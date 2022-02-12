package com.finalproject.Ecommerce.service.implementation;

import com.finalproject.Ecommerce.builder.CartBuilder;
import com.finalproject.Ecommerce.model.document.Cart;
import com.finalproject.Ecommerce.model.document.CartItem;
import com.finalproject.Ecommerce.model.document.Product;
import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.CartRequest;
import com.finalproject.Ecommerce.model.response.CartResponse;
import com.finalproject.Ecommerce.repository.CartRepository;
import com.finalproject.Ecommerce.repository.ProductRepository;
import com.finalproject.Ecommerce.repository.UserRepository;
import com.finalproject.Ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    @Override
    public CartResponse createCart(CartRequest cartRequest) throws ApiRestException {
        validateEmail(cartRequest.getEmail());
        var cartSaved = cartRepository.save(CartBuilder.requestToDocument(cartRequest));
        return CartBuilder.documentToResponse(cartSaved);
    }

    @Override
    public List<CartResponse> getAllCarts() {
        return CartBuilder.documentListToResponse(cartRepository.findAll());
    }

    @Override
    public CartResponse updateCartItem(Integer orderNumber, CartItem cartItemUpdated) throws ApiRestException {
        Cart cartFound = getCartByOrderNumber(orderNumber); // cart which contains the cart item to update
        validateProductExistsInRepository(cartItemUpdated);
        CartItem cartItemToUpdate = findCartItem(cartFound, cartItemUpdated.getCode());
        cartFound.getItems().remove(cartItemToUpdate);
        cartFound.getItems().add(cartItemUpdated);
        return CartBuilder.documentToResponse(cartRepository.save(cartFound));
    }

    @Override
    public void deleteCart(Integer orderNumber) throws ApiRestException {
        Cart cartFound = getCartByOrderNumber(orderNumber);
        cartRepository.delete(cartFound);
    }

    @Override
    public void deleteCartItem(Integer orderNumber, String code) throws ApiRestException {
        Cart cartFound = getCartByOrderNumber(orderNumber);
        CartItem cartItemToRemove = findCartItem(cartFound, code);
        cartFound.getItems().remove(cartItemToRemove);
        cartRepository.save(cartFound);
    }

    @Override
    public CartResponse addItem(Integer orderNumber, CartItem cartItem) throws ApiRestException {
        Cart cartFound = getCartByOrderNumber(orderNumber);
        validateProductExistsInRepository(cartItem);
        cartFound.getItems().add(cartItem);
        cartRepository.save(cartFound);
        return CartBuilder.documentToResponse(cartFound);
    }

    @Override
    public List<CartItem> getAllItems(Integer orderNumber) throws ApiRestException {
        return getCartByOrderNumber(orderNumber).getItems();
    }

    @Override
    public List<CartItem> getCartItemsByOrderNumber(Integer orderNumber) throws ApiRestException {
        return getCartByOrderNumber(orderNumber).getItems();
    }

    public Cart getCartByOrderNumber(Integer orderNumber) throws ApiRestException{
        Cart cart = cartRepository.findByOrderNumber(orderNumber);
        if(Objects.isNull(cart)){
            throw new ApiRestException(orderNumber.toString(), "Cart with that order number does not exist");
        }
        return cart;
    }

    public void validateEmail(String email) throws ApiRestException{
        var existingCart = cartRepository.findByEmail(email);
        var existingEmail = userRepository.findByEmail(email);
        if(Objects.nonNull(existingCart)){
            throw new ApiRestException(email, "Existing cart with this email");
        }
        if(Objects.isNull(existingEmail)){
            throw new ApiRestException(email, "Unregistered user email");
        }
    }

    public CartItem findCartItem(Cart cart, String code) throws ApiRestException{
        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getCode().equals(code)){
                return cartItem;
            }
        }
        throw new ApiRestException(code, "The product to update is not in the cart");
    }

    public void validateProductExistsInRepository(CartItem cartItem) throws ApiRestException{ // if item code exists in product repository then there is stock
        Product productFound = productRepository.findByCode(cartItem.getCode());
        if(Objects.isNull(productFound)){
            throw new ApiRestException(cartItem.getCode(), "The product code does not exist in the product repository");
        }
    }
}
