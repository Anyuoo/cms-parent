package com.anyu.articleservice.feign;

import com.anyu.articleservice.feign.fallback.CacheFeignClientFallbackFactory;
import com.anyu.common.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cache-service",path = "/cache",fallbackFactory = CacheFeignClientFallbackFactory.class)
public interface CacheFeignClient {

    @GetMapping("test")
    CommonResult test();
}
