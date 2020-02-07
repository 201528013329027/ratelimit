package com.demo.ratelimit.config;

import com.demo.ratelimit.constants.LimitConstants;
import com.demo.ratelimit.handle.RateLimiter;
import com.demo.ratelimit.handle.impl.RedisRateLimiterCounterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:19
 */
@Slf4j
@Configuration
public class EnableRateLimitConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "rateLimiter")
    @ConditionalOnProperty(prefix = LimitConstants.PREFIX, name = "algorithm", havingValue = "counter")
    public RateLimiter tokenRateLimiter(){
        DefaultRedisScript<Long> consumeRedisScript = new DefaultRedisScript<>();
        consumeRedisScript.setResultType(Long.class);
        consumeRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis-ratelimiter-counter.lua")));
        return new RedisRateLimiterCounterImpl(consumeRedisScript);
    }
}
