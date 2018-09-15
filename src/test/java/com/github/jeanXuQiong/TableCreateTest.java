package com.github.jeanXuQiong;


import com.github.jeanXuQiong.util.tablemsg.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class TableCreateTest {
    @Test
    public void createTable() throws SQLException, ClassNotFoundException {

        String packageName = "";
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "qwerasdf";
        Assert.assertEquals(true,InitTable.getInstance(packageName,driverClassName,url,user,password).init());
        Assert.assertEquals(false,InitTable.getInstance(packageName,driverClassName,url,user,password).init());
        Assert.assertEquals(true,InitTable.getInstance(packageName,driverClassName,url,user,password,true).init());
    }
}
