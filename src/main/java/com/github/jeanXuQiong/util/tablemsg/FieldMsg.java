package com.github.jeanXuQiong.util.tablemsg;

public class FieldMsg {


    /**
     * 名称
     * @return
     */
    private String  name;

    /**
     * 数据类型
     * @return
     */
     private String  type;

    /**
     *  长度
     * @return
     */
    private  Integer length;

    /**
     * 小数点
     * @return
     */
    private Integer decimalPoint;

    /**
     * 默认值
     * @return
     */
     private String  defaultValue;

    /**
     * 是否为空，默认为空
     * @return
     */
     private boolean notNull;

    /**
     * 自动增长
     */
    private boolean autoIncrement;

    /**
     * 注释
     * @return
     */
     private String  comments;

    /**
     * 字符集
     * @return
     */
     private String  charcter;

    /**
     * 默认排序
     * @return
     */
     private String  defaultCollation;

    /**
     * 无符号
     * @return
     */
    private boolean isUnsigned;
    /**
     * 是否补充0
     * @return
     */
    private boolean isZeroFill;

    private  boolean isPrimaryKey;

    private Integer primaryKeyLength;

    private String[] SpecifiedValues;

    private boolean currentTimestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDecimalPoint() {
        return decimalPoint;
    }

    public void setDecimalPoint(Integer decimalPoint) {
        this.decimalPoint = decimalPoint;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCharcter() {
        return charcter;
    }

    public void setCharcter(String charcter) {
        this.charcter = charcter;
    }

    public String getDefaultCollation() {
        return defaultCollation;
    }

    public void setDefaultCollation(String defaultCollation) {
        this.defaultCollation = defaultCollation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isUnsigned() {
        return isUnsigned;
    }

    public void setUnsigned(boolean unsigned) {
        isUnsigned = unsigned;
    }

    public boolean isZeroFill() {
        return isZeroFill;
    }

    public void setZeroFill(boolean zeroFill) {
        isZeroFill = zeroFill;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Integer getPrimaryKeyLength() {
        return primaryKeyLength;
    }

    public void setPrimaryKeyLength(Integer primaryKeyLength) {
        this.primaryKeyLength = primaryKeyLength;
    }

    public String[] getSpecifiedValues() {
        return SpecifiedValues;
    }

    public void setSpecifiedValues(String[] specifiedValues) {
        SpecifiedValues = specifiedValues;
    }

    public boolean isCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(boolean currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }
}
