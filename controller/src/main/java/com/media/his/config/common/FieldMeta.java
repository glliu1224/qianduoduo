package com.media.his.config.common;

import java.lang.annotation.*;

/**
 * @author shouhan
 * @version 1.0
 * @date 2018/03/23
 * @desc 自定义注解, 标记字段的意思
 * @see
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD, ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface FieldMeta {

    /**
     * 字段名称
     */
    String name() default "";

}