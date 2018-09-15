package com.github.jeanXuQiong.mybatis.test.entity;


import com.github.jeanXuQiong.util.tablemsg.annotation.Field;
import com.github.jeanXuQiong.util.tablemsg.FieldType;
import com.github.jeanXuQiong.util.tablemsg.annotation.Table;

@Table("user")
public class User {
    @Field(value = "user_id",type = FieldType.BIGINT,length = 18,notNull = true,isPrimaryKey = true,isAutoIncrement = true)
    private Long id;

    @Field(value = "user_sex")
    private String userSex;

    @Field("nick_name")
    private String nickName;

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "userSex='" + userSex + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
