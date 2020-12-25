package com.anyu.articleservice.service.impl;

import com.anyu.articleservice.entity.Article;
import com.anyu.articleservice.service.VideoService;
import org.springframework.stereotype.Service;

@Service("videoService")
public class ServiceImpl implements VideoService {
    @Override
    public Article getVideoByArticleId(String articleId) {
        return null;
    }
}
