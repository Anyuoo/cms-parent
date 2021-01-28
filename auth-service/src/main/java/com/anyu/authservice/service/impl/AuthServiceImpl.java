package com.anyu.authservice.service.impl;

import com.anyu.authservice.entity.User;
import com.anyu.authservice.service.AuthService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthServiceImpl implements AuthService {
    @Override
    public Optional<User> auth(String username, String password) {
        User user = new User();
        user.setUsername("anyu")
                .setLevel("high")
                .setPassword("123456");
        if ("anyu".equals(username) && "123456".equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
