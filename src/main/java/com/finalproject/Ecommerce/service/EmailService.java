package com.finalproject.Ecommerce.service;

import com.finalproject.Ecommerce.model.document.Product;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmail();
    void sendOrderEmail(List<Product> cartItems) throws MessagingException;
}
