package com.media.his.config.common;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DuplicateSubmitToken {
    String key() default "";
    int expire() default 5;
}
