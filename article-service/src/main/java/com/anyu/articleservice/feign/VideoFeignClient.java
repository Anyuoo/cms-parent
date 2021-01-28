package com.anyu.articleservice.feign;

import com.anyu.articleservice.model.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "video-service",path = "/video")
public interface VideoFeignClient {

    @GetMapping("/{videoId}")
    Video getVideoById( @PathVariable("videoId") int videoId);

    @GetMapping("/hello")
     String hello();
}
