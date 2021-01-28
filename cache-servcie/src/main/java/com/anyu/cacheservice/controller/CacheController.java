package com.anyu.cacheservice.controller;

import com.anyu.common.result.advice.annotation.CommonResultType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonResultType
public class CacheController {
    @GetMapping("/test")
    public int test() {
        return 1000;
    }
}
