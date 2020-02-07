package com.demo.ratelimit.algorithm.impl;


import com.demo.ratelimit.algorithm.RateLimiterAlgorithm;
import com.demo.ratelimit.constants.LimitConstants;
import com.demo.ratelimit.handle.RateLimiter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("rateLimiter")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = LimitConstants.PREFIX, name = "algorithm", havingValue = "counter", matchIfMissing = true)
public class CounterAlgorithmImpl implements RateLimiterAlgorithm {
    @NonNull
    private RateLimiter rateLimiter;


    public void consume(String key, long limit, long lrefreshInterval){
        rateLimiter.counterConsume(key,limit,lrefreshInterval);
    }
}
