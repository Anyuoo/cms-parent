package com.anyu.authservice.service;

import com.anyu.authservice.entity.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> auth(String username,String password);
}
