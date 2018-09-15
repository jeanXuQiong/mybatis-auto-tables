package com.github.jeanXuQiong.util.tablemsg.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Target(ElementType.PACKAGE)
public @interface Table {
    String value();

    /**
     * 字符集
     * @return
     */
    String charcter() default "utf8";
}
