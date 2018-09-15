package com.github.jeanXuQiong.util.tablemsg;

import java.sql.*;
import java.util.List;

public class SQLRunner {
    public boolean tableIsExist(TableMsg[] tables, String driverClassName, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class<?> dbC = Class.forName(driverClassName);
        Connection connection = DriverManager.getConnection(url, user, password);
        DatabaseMetaData metaData = connection.getMetaData();


        if (connection != null) {
            System.out.println("Database connection is successful！");
            for (TableMsg table : tables) {
                ResultSet rs = metaData.getTables(null, null, table.getValue(), new String[]{"TABLE"});
                if (rs.next()) {
                    System.out.println("Table is exited！");
                    closeConnection(connection);
                    return true;
                }
            }
        }

        closeConnection(connection);
        return false;
    }


    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException var2) {
            ;
        }
    }

    public void executeStatement(String[] sqls, String driverClassName, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class<?> dbC = Class.forName(driverClassName);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();


        if (connection != null) {
            System.out.println("Database connection is successful！");
            for (String sql : sqls) {
                int createReturn = statement.executeUpdate(sql);
                if (createReturn == 0) {
                    System.out.println("Table create success！");
                }
            }

        }

        closeConnection(connection);
    }

}
