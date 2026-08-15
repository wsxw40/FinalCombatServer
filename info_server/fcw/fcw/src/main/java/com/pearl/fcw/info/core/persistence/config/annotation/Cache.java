package com.pearl.fcw.info.core.persistence.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.pearl.fcw.info.core.persistence.config.CacheStrategy;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    CacheStrategy strategy() default CacheStrategy.MEMCACHED;

}
