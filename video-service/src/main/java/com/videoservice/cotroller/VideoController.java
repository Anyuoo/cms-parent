package com.videoservice.cotroller;

import com.anyu.common.advice.annotation.CommonResultType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CommonResultType
public class VideoController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
