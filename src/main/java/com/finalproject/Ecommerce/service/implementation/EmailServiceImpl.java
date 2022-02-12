package com.finalproject.Ecommerce.service.implementation;

import com.finalproject.Ecommerce.model.document.CartItem;
import com.finalproject.Ecommerce.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail() {
        var message = new SimpleMailMessage();
        message.setTo("testuser4javaproject@gmail.com");
        message.setSubject("Welcome - Java Backend Project: E-commerce");
        message.setText("This is an email sent to notify that a new user was created");
        javaMailSender.send(message);
    }

    @Override
    public List<CartItem> sendOrderEmail(List<CartItem> cartItems) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo("testuser4javaproject@gmail.com");
        helper.setSubject("New order - Java Backend Project: E-commerce");
        helper.setText("This is an email sent to notify the creation of a new order: " + cartItemsInHTML(cartItems));
        return cartItems;
    }

    public String cartItemsInHTML(List<CartItem> cartItems){
        StringBuilder str = new StringBuilder();
        appendLine(str);
        str.append("<h2> Order products: </h2>");
        for(CartItem cart: cartItems){
            str.append("<ul><li> Product Code: ");
            str.append(cart.getCode());
            str.append("</li>");
            str.append("<li> Product Quantity: ");
            str.append(cart.getCode());
            str.append("</li></ul>");
            appendLine(str);
        }
        return str.toString();
    }

    public void appendLine(StringBuilder str){
        str.append(System.getProperty("line.separator"));
    }
}
