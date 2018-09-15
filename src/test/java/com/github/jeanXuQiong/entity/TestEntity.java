package com.github.jeanXuQiong.entity;


import com.github.jeanXuQiong.util.tablemsg.annotation.Field;
import com.github.jeanXuQiong.util.tablemsg.FieldType;
import com.github.jeanXuQiong.util.tablemsg.annotation.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table("test1")
public class TestEntity {
    @Field(value = "id",type = FieldType.BIGINT,length = 18,notNull = true,isPrimaryKey = true,isAutoIncrement = true,comments = "ID")
    private Long id;
    @Field(value = "id1",type = FieldType.BIGINT,length = 18,isZeroFill = true)
    private Long id1;
    @Field(value = "id2",type = FieldType.BIGINT,length = 18,isUnsigned = true)
    private Long id2;

    @Field(value = "dob",type = FieldType.DOUBLE,length = 5,decimalPoint = 2,isUnsigned = true,isZeroFill = true,isAutoIncrement = true)
    private Double dob;
    @Field(value = "dob1",type = FieldType.DOUBLE,length = 5,decimalPoint = 2)
    private Double dob1;

    @Field(value = "bdob",type = FieldType.DECIMAL,length = 5,decimalPoint = 2)
    private BigDecimal bdob;
    @Field(value = "bdob1",type = FieldType.DECIMAL,length = 5,decimalPoint = 2,isUnsigned = true,isZeroFill = true)
    private BigDecimal bdob1;

    @Field(value = "mediumtext1",type = FieldType.MEDIUMTEXT,comments = "mediumtext1")
    private String mediumtext1;

    @Field(value = "name",comments = "name",length = 100)
    private String name;
    @Field(value = "nameKey",comments = "name",length = 100,isPrimaryKey = true,primaryKeyLength = 100)
    private String nameKey;

    @Field(value = "set",type = FieldType.SET,comments = "set",length = 100,isPrimaryKey = true,specifiedValues = {"abc","def"})
    private String set;

    @Field(value = "timestamp1",type = FieldType.TIMESTAMP,comments = "TIMESTAMP",currentTimestamp = true)
    private Timestamp times;

    @Field(value = "bit",type = FieldType.BIT,defaultValue = "false",comments = "bit")
    private boolean bit;

    @Field(value = "date1",type = FieldType.DATE,comments = "DATE")
    private Date date1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId1() {
        return id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }

    public Double getDob() {
        return dob;
    }

    public void setDob(Double dob) {
        this.dob = dob;
    }

    public Double getDob1() {
        return dob1;
    }

    public void setDob1(Double dob1) {
        this.dob1 = dob1;
    }

    public BigDecimal getBdob() {
        return bdob;
    }

    public void setBdob(BigDecimal bdob) {
        this.bdob = bdob;
    }

    public BigDecimal getBdob1() {
        return bdob1;
    }

    public void setBdob1(BigDecimal bdob1) {
        this.bdob1 = bdob1;
    }

    public String getMediumtext1() {
        return mediumtext1;
    }

    public void setMediumtext1(String mediumtext1) {
        this.mediumtext1 = mediumtext1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public Timestamp getTimes() {
        return times;
    }

    public void setTimes(Timestamp times) {
        this.times = times;
    }

    public boolean isBit() {
        return bit;
    }

    public void setBit(boolean bit) {
        this.bit = bit;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
}
