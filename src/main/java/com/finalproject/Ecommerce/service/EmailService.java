package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.response.CartItemResponse;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmail();
    void sendOrderEmail(List<CartItemResponse> cartItems) throws MessagingException;
}
