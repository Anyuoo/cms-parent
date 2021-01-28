package com.videoservice.cotroller;

import com.videoservice.model.Video;
import com.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     *获取视频
     */
    @GetMapping("/{videoId}")
    public Video getVideoById(@PathVariable("videoId") int videoId) {
        return videoService.getVideoById(videoId).orElseThrow();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
