package com.github.jeanXuQiong.util.tablemsg.annotation;

import com.github.jeanXuQiong.util.tablemsg.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {

    /**
     * 名称
     * @return
     */
    String value() default "";

    /**
     * 数据类型
     * @return
     */
    String type() default FieldType.VARCHAR;

    /**
     *  长度
     * @return
     */
    int length() default 255;

    /**
     * 小数点
     * @return
     */
    int decimalPoint() default 0;

    /**
     * 默认值
     * @return
     */
    String defaultValue() default "";

    /**
     * 是否为空，默认为空
     * @return
     */
    boolean notNull() default false;

    /**
     * 注释
     * @return
     */
    String comments() default "";

    /**
     * 字符集
     * @return
     */
    String charcter() default "utf8";

    /**
     * 默认排序
     * @return
     */
    String defaultCollation() default "utf8_general_ci";

    /**
     * 是否主键
     * @return
     */
    boolean isPrimaryKey() default false;
    /**
     * 主键长度
     * @return
     */
    int primaryKeyLength() default 0;

    /**
     * 是否自动增长
     * @return
     */
    boolean isAutoIncrement() default false;
    /**
     * 无符号
     * @return
     */
    boolean isUnsigned() default false;
    /**
     * 是否补充0
     * @return
     */
    boolean isZeroFill() default false;

    String[] specifiedValues() default {};

    boolean currentTimestamp() default false;
}
