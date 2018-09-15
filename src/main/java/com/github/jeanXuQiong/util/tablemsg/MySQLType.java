package com.github.jeanXuQiong.util.tablemsg;

import com.sun.org.apache.bcel.internal.classfile.Code;

public enum MySQLType {

    //默认 注释 无符号 填充0 长度  自动递增
    BIGINT("BIGINT"),
    TINYINT("TINYINT"),
    SMALLINT("SMALLINT"),// `eqw`  smallint(4321) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'qwe' (""),
    MEDIUMINT("MEDIUMINT"),
    INT("INT"),
    INTEGER("INTEGER"),

    //默认 注释 无符号 填充0 长度  小数 自动递增
    REAL("REAL"),
    DOUBLE("DOUBLE"),
    FLOAT("FLOAT"),//`eqw`  float(4321(""),32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'qwe' (""),


    //默认 注释 无符号 填充0 长度  小数
    DECIMAL("DECIMAL"),
    NUMERIC("NUMERIC"), // `eqw`  numeric(4321(""),32) UNSIGNED ZEROFILL NOT NULL COMMENT 'qwe'

    //默认 注释 键长度 （无长度）
    TINYBLOB("TINYBLOB"),
    BLOB("BLOB"),
    MEDIUMBLOB("MEDIUMBLOB"),
    LONGBLOB("LONGBLOB"),

    //默认 注释   字符 排序 键长度 长度
    CHAR("CHAR"),
    VARCHAR("VARCHAR"),

    //默认 注释   字符 排序 键长度 （无长度）
    TINYTEXT ("TINYTEXT"),
    TEXT("TEXT"),
    MEDIUMTEXT("MEDIUMTEXT"),
    LONGTEXT("LONGTEXT"),  // PRIMARY KEY (`eqw`(123321))

    //默认 注释  值  字符 排序 （无长度）
    SET("SET"),  //指定数据存储，非指定不能存入 `eqw`  set('ewqew'(""),'eqweqe') NULL DEFAULT ''
    ENUM("ENUM"),  //`eqw`  enum('ewqew'(""),'eqweqe') NULL DEFAULT ''

    //默认  注释（有长度） 时间戳
    TIMESTAMP("TIMESTAMP"),  //`eqw`  timestamp(4321) NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT 'qwe'


    //默认  注释（有长度）
    BIT("BIT"), //`eqw`  bit(4321) NULL AUTO_INCREMENT COMMENT 'qwe'
    TIME("TIME"),
    DATETIME("DATETIME"), //`eqw`  datetime(4321) NOT NULL


    //默认  注释（无长度）
    DATE("DATE"),
    YEAR("YEAR"),
    BINARY("BINARY"),
    VARBINARY("VARBINARY"),
    POINT("POINT"),
    LINESTRING("LINESTRING"),
    POLYGON("POLYGON"),
    GEOMETRY("GEOMETRY"),
    MULTIPOINT("MULTIPOINT"),
    MULTILINESTRING("MULTILINESTRING"),
    MULTIPOLYGON("MULTIPOLYGON"),
    GEOMETRYCOLLECTION("GEOMETRYCOLLECTION");

    private MySQLType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static MySQLType getByValue(String value) {
        for (MySQLType type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
