package com.ic.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)/*注解作用域*/
@Retention(RetentionPolicy.RUNTIME)/*表示注解类型保留时间的长短*/
@Documented
public @interface DataSource
{
    String value() default "dataSource";
}
