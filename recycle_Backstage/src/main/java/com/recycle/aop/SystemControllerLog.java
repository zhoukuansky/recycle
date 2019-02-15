package com.recycle.aop;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Getter
//@Setter
//@ToString
public @interface SystemControllerLog {
    String logAction() default "";

    String logContent() default "";
}