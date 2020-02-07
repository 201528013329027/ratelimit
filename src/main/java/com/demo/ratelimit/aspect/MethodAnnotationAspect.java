package com.demo.ratelimit.aspect;

import com.demo.ratelimit.algorithm.RateLimiterAlgorithm;
import com.demo.ratelimit.annotation.CheckTypeEnum;
import com.demo.ratelimit.annotation.MethodRateLimit;
import com.demo.ratelimit.exception.LimitRateErrorEnum;
import com.demo.ratelimit.exception.LimitRateException;
import com.demo.ratelimit.util.RateLimiterUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:53
 */
@Slf4j
@Aspect
@Component
public class MethodAnnotationAspect {
    private static final Logger BLACK_LOG = LoggerFactory.getLogger("BLACK_LOG");
    @Autowired
    private RateLimiterAlgorithm rateLimiterAlgorithm;

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(methodRateLimit)")
    public void annotationPointcut(MethodRateLimit methodRateLimit) {

    }

    @Before("annotationPointcut(methodRateLimit)")
    public void doBefore(JoinPoint joinPoint, MethodRateLimit methodRateLimit) {
        String key = null;
        long limit = methodRateLimit.limit();
        long refreshInterval = methodRateLimit.refreshInterval();
        CheckTypeEnum checkTypeEnum = methodRateLimit.checkType();
        try{
            key= RateLimiterUtil.getRateKey(joinPoint,checkTypeEnum, true);
            log.debug("限流key:{}", key);
            rateLimiterAlgorithm.consume(key, limit,refreshInterval);
        }catch (LimitRateException e){
           throw e;
        }catch (Exception e){
            log.error("限流错误", e);
        }
    }
}
