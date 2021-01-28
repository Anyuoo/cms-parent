package com.videoservice.service.impl;

import com.videoservice.model.Video;
import com.videoservice.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("videoService")
public class VideoServiceImpl implements VideoService {
    private List<Video> videos;

   private void initStorage() {
       if (videos != null && !videos.isEmpty()) {
           return;
       }
       this.videos = new ArrayList<>();
       for (int i = 0; i < 20; i++) {
           Video video = new Video();
           video.setId(i);
           video.setName("video" + i);
           videos.add(video);
       }
   }

    @Override
    public Optional<Video> getVideoById(int videoId) {
       initStorage();
        return videos.stream()
                .filter(video -> video.getId() == videoId)
                .findFirst();
    }


}
