package com.finalproject.Ecommerce.model.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("users")
public class User {
    @Id
    private String id;
    private String email;
    private String phone;
    private String name;
    private String username;
    private String password;






}
