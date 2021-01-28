package com.videoservice.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("test")
    public String doTest() {
        return "test";
    }
    @GetMapping("/test1")
    public String doTest1() {
        return "test1";
    }
}
