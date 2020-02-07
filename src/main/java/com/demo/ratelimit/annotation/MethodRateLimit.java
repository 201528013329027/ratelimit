package com.demo.ratelimit.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author:zhaiyarong
 * @Date:2019/12/31 14:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodRateLimit {
    /**
     *
     * @return CheckTypeEnum 限流类型。默认值：ALL。可选值：ALL,IP,USER,CUSTOM
     */
    CheckTypeEnum checkType() default CheckTypeEnum.ALL;
    /**
     *
     * @return 限流次数。默认值10
     */
    long limit() default 60;
    /**
     *
     * @return 限流时间间隔,以秒为单位。默认值60
     */
    long refreshInterval() default 60;

    /**--------------------限流算法为令牌桶时的有效配置-----------------------**/
    /**
     *
     * @return 向令牌桶中添加数据的时间间隔,以秒为单位。默认值10秒
     */
    long tokenBucketTimeInterval() default 10;
    /**
     *
     * @return 每次为令牌桶中添加的令牌数量。默认值5个
     */
    long tokenBucketStepNum() default 5;
}
