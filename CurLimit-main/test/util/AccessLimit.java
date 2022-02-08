package com.lixinming.test.util;

/**
 * @Author lixinming
 * @Date 2022/1/27 14:59
 * @Version 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 基于注解的请求限制
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    // 资源名称，用于描述接口功能
    String name() default "";
    // 资源 key
    String key() default "";
    // key prefix
    String prefix() default "";
    // 时间的，单位秒
    int period() default 60;
    // 限制访问次数
    int count() default 0;


}
