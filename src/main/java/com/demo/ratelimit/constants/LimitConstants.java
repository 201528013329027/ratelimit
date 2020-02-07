package com.demo.ratelimit.constants;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:23
 */
public class LimitConstants {
    /**
     * 配置rateLimit的前缀
     */
    public static final String PREFIX = "rate.limit";

    /**
     * 集群模式指定slot的hash tag
     */
    public static final String HASH_TAG="rate:limit";
    /**
     * hash tag 前缀
     */
    public static final String HASH_TAG_PRFIX="{";
    /**
     * hash tag 后缀
     */
    public static final String HASH_TAG_SUFFIX="}";

    /**
     * 自定义拦截方式时的key
     */
    public static final String CUSTOM="rateLimit-custom";
    /**
     * redis返回错误信息
     */
    public static final String REDIS_ERROR="-1";
}
