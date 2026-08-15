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

    /**
     * 分表依据的字段名
     * @return
     */
    String segFieldName() default "";

    /**
     * 声明每张表的最大分区。只有在type=Schema.EXT时有效
     * @return
     */
    int seg() default 1;
}
