package com.videoservice.service;

import com.videoservice.model.Video;

import java.util.Optional;

public interface VideoService {
    Optional<Video> getVideoById(int videoId);
}
