package com.ic.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component("output")
public @interface Output {
    String value() default "abc";
}
