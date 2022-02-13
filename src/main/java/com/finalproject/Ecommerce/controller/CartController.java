package com.finalproject.Ecommerce.controller;

import com.finalproject.Ecommerce.model.document.CartItem;
import com.finalproject.Ecommerce.model.exceptions.ApiRestException;
import com.finalproject.Ecommerce.model.request.CartRequest;
import com.finalproject.Ecommerce.model.response.CartItemResponse;
import com.finalproject.Ecommerce.model.response.CartResponse;
import com.finalproject.Ecommerce.service.CartService;
import com.finalproject.Ecommerce.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final EmailService emailService;

    @PostMapping("/test")
    public void testEmail(){
        emailService.sendEmail();
    }

    @PostMapping
    public CartResponse createCart(@RequestBody CartRequest cartRequest) throws ApiRestException{
        return cartService.createCart(cartRequest);
    }
    @GetMapping
    public List<CartResponse> getAllCarts(){
        return cartService.getAllCarts();
    }

    @PostMapping("/add/{orderNumber}")
    public CartResponse addProduct(@PathVariable Integer orderNumber, @RequestBody CartItem item) throws ApiRestException{
        return cartService.addItem(orderNumber,item);
    }

    @PostMapping("/{orderNumber}")
    public void generateOrderEmail(@PathVariable Integer orderNumber) throws ApiRestException, MessagingException{
        emailService.sendOrderEmail(cartService.getCartItemsByOrderNumber(orderNumber));
    }

    @GetMapping("/{orderNumber}")
    public List<CartItemResponse> getAllItems(@PathVariable Integer orderNumber) throws ApiRestException{
        return cartService.getCartItemsByOrderNumber(orderNumber);
    }
    @PutMapping("/{orderNumber}")
    public CartResponse updateCartItem(@PathVariable Integer orderNumber,@RequestBody CartItem cartItem)throws ApiRestException{
        return cartService.updateCartItem(orderNumber,cartItem);
    }

    @DeleteMapping("/{orderNumber}")
    public void deleteCartItem(@PathVariable Integer orderNumber,@RequestParam String code) throws ApiRestException{
        cartService.deleteCartItem(orderNumber,code);
    }

    @DeleteMapping("/order/{orderNumber}")
    public void deleteCart(@PathVariable Integer orderNumber)throws ApiRestException {
        cartService.deleteCart(orderNumber);
    }
}
