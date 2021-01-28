package com.anyu.articleservice.feign.fallback;

import com.anyu.articleservice.feign.CacheFeignClient;
import com.anyu.common.result.CommonResult;
import com.anyu.common.result.ResultType;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CacheFeignClientFallbackFactory implements FallbackFactory<CacheFeignClient> {
    @Override
    public CacheFeignClient create(Throwable cause) {
        return () -> CommonResult.failure(ResultType.SENTINEL_FLOW_ERROR,1024);
    }
}
