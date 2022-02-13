package com.finalproject.Ecommerce.service.implementation;

import com.finalproject.Ecommerce.model.response.CartItemResponse;
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
    public void sendOrderEmail(List<CartItemResponse> cartItems) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo("testuser4javaproject@gmail.com");
        helper.setSubject("New order - Java Backend Project: E-commerce");
        helper.setText("This is an email sent to notify the creation of a new order: " + cartItemsInHTML(cartItems), true);
        javaMailSender.send(mimeMessage);
    }

    public String cartItemsInHTML(List<CartItemResponse> cartItem){
        StringBuilder str = new StringBuilder();
        double orderTotal = 0d;
        appendLine(str);
        str.append("<h2> Order products: </h2>");
        for(CartItemResponse item: cartItem){
            double totalByItem = 0d;
            str.append("<ul><li><b>Description: </b> ");
            str.append(item.getDescription());
            str.append("</li>");
            str.append("<li><b>Category: </b>");
            str.append(item.getCategory());
            str.append("</li>");
            str.append("<li><b>Price:</b> $ ");
            str.append(item.getPrice());
            str.append("</li>");
            str.append("<li><b>Quantity: </b>");
            str.append(item.getQuantity());
            str.append("</li>");
            str.append("<li><b>Code: </b>");
            str.append(item.getCode());
            appendLine(str);
            totalByItem = item.getPrice() * item.getQuantity();
            str.append(String.format("<li><b> Total : </b> $ %1.2f", totalByItem));
            str.append("</li></ul>");
            orderTotal += totalByItem;
            appendLine(str);
            str.append("<hr>");
        }
        str.append(String.format("<h2> Order total: $ %1.2f </h2>", orderTotal));
        return str.toString();
    }

    public void appendLine(StringBuilder str){
        str.append(System.getProperty("line.separator"));
    }
}
