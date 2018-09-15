package com.github.jeanXuQiong.util.tablemsg;

public class FieldType {

    //默认 注释 无符号 填充0 长度  自动递增
    public final static String BIGINT = "BIGINT";
    public final static String TINYINT = "TINYINT";
    public final static String SMALLINT = "SMALLINT"; // `eqw`  smallint(4321) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'qwe' ,
    public final static String MEDIUMINT = "MEDIUMINT";
    public final static String INT = "INT";
    public final static String INTEGER = "INTEGER";

    //默认 注释 无符号 填充0 长度  小数 自动递增
    public final static String REAL = "REAL";
    public final static String DOUBLE = "DOUBLE";
    public final static String FLOAT = "FLOAT"; //`eqw`  float(4321,32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT 'qwe' ,


    //默认 注释 无符号 填充0 长度  小数
    public final static String DECIMAL = "DECIMAL";
    public final static String NUMERIC = "NUMERIC";  // `eqw`  numeric(4321,32) UNSIGNED ZEROFILL NOT NULL COMMENT 'qwe'

    //默认 注释 键长度 （无长度）
    public final static String TINYBLOB = "TINYBLOB";
    public final static String BLOB = "BLOB";
    public final static String MEDIUMBLOB = "MEDIUMBLOB";
    public final static String LONGBLOB = "LONGBLOB";

    //默认 注释   字符 排序 键长度 长度
    public final static String CHAR = "CHAR";
    public final static String VARCHAR = "VARCHAR";

    //默认 注释   字符 排序 键长度 （无长度）
    public final static String TINYTEXT = "TINYTEXT";
    public final static String TEXT = "TEXT";
    public final static String MEDIUMTEXT = "MEDIUMTEXT";
    public final static String LONGTEXT = "LONGTEXT";  // PRIMARY KEY (`eqw`(123321))

    //默认 注释  值  字符 排序 （无长度）
    public final static String SET = "SET";  //指定数据存储，非指定不能存入 `eqw`  set('ewqew','eqweqe') NULL DEFAULT ''
    public final static String ENUM = "ENUM";  //`eqw`  enum('ewqew','eqweqe') NULL DEFAULT ''

    //默认  注释（有长度） 时间戳
    public final static String TIMESTAMP = "TIMESTAMP";  //`eqw`  timestamp(4321) NOT NULL DEFAULT '' ON UPDATE CURRENT_TIMESTAMP COMMENT 'qwe'


    //默认  注释（有长度）
    public final static String BIT = "BIT"; //`eqw`  bit(4321) NULL AUTO_INCREMENT COMMENT 'qwe'
    public final static String TIME = "TIME";
    public final static String DATETIME = "DATETIME"; //`eqw`  datetime(4321) NOT NULL


    //默认  注释（无长度）
    public final static String DATE = "DATE";
    public final static String YEAR = "YEAR";
    public final static String BINARY = "BINARY";
    public final static String VARBINARY = "VARBINARY";
    public final static String POINT = "POINT";
    public final static String LINESTRING = "LINESTRING";
    public final static String POLYGON = "POLYGON";
    public final static String GEOMETRY= "GEOMETRY";
    public final static String MULTIPOINT = "MULTIPOINT";
    public final static String MULTILINESTRING = "MULTILINESTRING";
    public final static String MULTIPOLYGON = "MULTIPOLYGON";
    public final static String GEOMETRYCOLLECTION = "GEOMETRYCOLLECTION";

}
