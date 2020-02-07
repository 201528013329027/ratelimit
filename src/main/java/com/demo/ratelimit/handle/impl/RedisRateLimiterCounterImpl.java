package com.demo.ratelimit.handle.impl;


import com.demo.ratelimit.constants.LimitConstants;
import com.demo.ratelimit.exception.LimitRateErrorEnum;
import com.demo.ratelimit.exception.LimitRateException;
import com.demo.ratelimit.handle.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:34
 */
@Slf4j
public class RedisRateLimiterCounterImpl extends RateLimiter {
    @Autowired
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    public RedisRateLimiterCounterImpl(DefaultRedisScript redisScript){
        this.redisScript = redisScript;
    }

    public void counterConsume(String key, long limit, long lrefreshInterval) throws LimitRateException {
        List<Object> keyList = new ArrayList<>();
        keyList.add(key);
        String result = redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(), keyList, limit+"", lrefreshInterval+"").toString();

        if(LimitConstants.REDIS_ERROR.equals(result)){
            throw new LimitRateException(LimitRateErrorEnum.TOO_MANY_REQUESTS);
        }
    }
}
