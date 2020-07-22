package com.example.study;


import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalCache  {
    /**
     * 缓存key
     *
     * @return
     */
    String key() default "";

    /**
     * 是否缓存空值
     *
     * @return
     */
    boolean cacheNull() default false;

    /**
     * 生存时间，单位是秒，默认为-1(永不过期)
     *
     * @return
     */
    int ttl() default -1;

    /**
     * 生存状态
     *
     * true:每访问一次，将刷新存活时间
     *
     * false:不刷新存活时间，时间一到就清除
     *
     * @return
     */
    boolean state() default true;

}
