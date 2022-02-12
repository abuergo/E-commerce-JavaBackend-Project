package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.document.CartItem;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmail();
    List<CartItem> sendOrderEmail(List<CartItem> cartItems) throws MessagingException;
}
