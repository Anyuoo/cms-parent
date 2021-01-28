package com.anyu.articleservice.controller;


import com.anyu.articleservice.feign.CacheFeignClient;
import com.anyu.articleservice.feign.VideoFeignClient;
import com.anyu.articleservice.model.Video;
import com.anyu.common.exception.GlobalException;
import com.anyu.common.jwt.annotation.JwtToken;
import com.anyu.common.result.CommonResult;
import com.anyu.common.result.ResultType;
import com.anyu.common.result.advice.annotation.CommonResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
@CommonResultType
public class ArticleController {
    @Autowired
    private VideoFeignClient videoFeignClient;
    @Autowired
    private CacheFeignClient cacheFeignClient;

    @GetMapping("/{articleId}")
    public Video getVideoByArticleId(@PathVariable("articleId") String articleId){
        return videoFeignClient.getVideoById(1);
    }

    @GetMapping("/ex")
    public String getEx() {
        throw  GlobalException.causeBy(ResultType.SENTINEL_FLOW_ERROR);
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "10");
        return map;
    }
    @GetMapping("/hello1")
    @JwtToken
    public CommonResult hello1() {
        return cacheFeignClient.test();
    }
}
