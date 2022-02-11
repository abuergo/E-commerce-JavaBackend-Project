package com.finalproject.Ecommerce.security;

import com.finalproject.Ecommerce.config.ApplicationProperties;
import com.finalproject.Ecommerce.utils.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final ApplicationProperties properties;

    public String getJWTToken(String usernameOrEmail) {
        var grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(Constants.ROLE);

        return Jwts.builder()
                .setSubject(usernameOrEmail)
                .claim(Constants.AUTHORITIES,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, properties.getJwtSecret().getBytes())
                .compact();
    }



}
