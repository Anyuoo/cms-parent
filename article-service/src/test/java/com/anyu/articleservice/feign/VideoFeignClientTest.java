package com.anyu.articleservice.feign;

import com.anyu.articleservice.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
class VideoFeignClientTest {
    @Autowired
    private VideoFeignClient videoFeignClient;

    @Test
    void getVideoById() {
        Video videoById = videoFeignClient.getVideoById(1);
    }
}