package com.github.jeanXuQiong;

import com.github.jeanXuQiong.util.tablemsg.*;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.SQLException;

//@SpringBootApplication
@MapperScan("com.github.jeanXuQiong.mybatis.**.dao")
public class MybatisInitAutoTableApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String packageName = "";
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "qwerasdf";

        InitTable.getInstance(packageName, driverClassName, url, user, password).init();
    }


}
