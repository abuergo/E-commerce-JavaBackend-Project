package com.finalproject.Ecommerce.config;

import com.finalproject.Ecommerce.security.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final ApplicationProperties properties;

    @Override
    protected void configure(HttpSecurity httpsecurity) throws Exception{
        httpsecurity
                .csrf().disable()
                .addFilterAfter(new JwtTokenFilter(properties), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
//                    .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .anyRequest().permitAll();

//                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
