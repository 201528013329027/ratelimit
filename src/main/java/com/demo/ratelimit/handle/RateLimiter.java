package com.demo.ratelimit.handle;


/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:29
 */
public abstract class RateLimiter {
    public void counterConsume(String key, long limit, long lrefreshInterval){

    }

    public void tokenConsume(String key, long limit, long lrefreshInterval, long tokenBucketStepNum, long tokenBucketTimeInterval){

    }
}
