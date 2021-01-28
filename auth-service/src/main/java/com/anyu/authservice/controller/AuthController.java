package com.anyu.authservice.controller;

import com.anyu.authservice.entity.User;
import com.anyu.authservice.service.AuthService;
import com.anyu.common.exception.GlobalException;
import com.anyu.common.jwt.JwtService;
import com.anyu.common.jwt.annotation.JwtToken;
import com.anyu.common.result.ResultType;
import com.anyu.common.result.advice.annotation.CommonResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@CommonResultType
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    @JwtToken(required = false)
    public Map<String,Object> auth(String username, String password) {
        final Optional<User> authUser = authService.auth(username, password);
        Map<String,Object> map = new HashMap<>();
        if (authUser.isPresent()) {
            Optional<String> jwt = jwtService.createJwt("1", authUser.get().getUsername(), authUser.get().getLevel());
            map.put("token", jwt.orElse(""));
        }
        User user = authUser.orElseThrow(() -> GlobalException.causeBy(ResultType.AUTH_FAILURE));
        map.put("user", user);
        return map;
    }
}
