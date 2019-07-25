package com.gwg.shiro.web.config.jdbc;

import java.lang.annotation.*;

/**
 * 多数据源注解
 * @author gwg
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
