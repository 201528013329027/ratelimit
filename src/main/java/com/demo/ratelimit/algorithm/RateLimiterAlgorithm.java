package com.demo.ratelimit.algorithm;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:57
 */
public interface RateLimiterAlgorithm {
    void consume(String key, long limit, long lrefreshInterval);
}
