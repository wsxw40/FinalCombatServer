package com.pearl.fcw.core.pojo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GoSchema {

    /**
     * 声明表类型
     * @return
     */
    Schema type() default Schema.MAIN;

}
