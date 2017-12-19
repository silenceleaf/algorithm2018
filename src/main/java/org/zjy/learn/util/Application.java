package org.zjy.learn.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Junyan Zhang on 12/14/2017.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface Application {
    String time();
    String name() default "";
}
