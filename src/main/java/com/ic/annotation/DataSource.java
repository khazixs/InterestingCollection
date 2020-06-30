package com.ic.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource
{

}
