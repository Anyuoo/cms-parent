package com.anyu.articleservice.controller;


import com.anyu.common.advice.annotation.CommonResultType;
import com.anyu.common.entity.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
@CommonResultType
public class ArticleController {

    @GetMapping("/{articleId}")
    public String getVideoByArticleId(@PathVariable("articleId") String articleId){
        return "1111";
    }

    @GetMapping("/hello")
    public Map<String,String> hello() {
        HashMap<String,String> map = new HashMap<>();
        map.put("a", "10");
        return map;
    }
    @GetMapping("/hello1")
    public String hello1() {
        return "5S5S";
    }
}
