package com.github.jeanXuQiong.util.tablemsg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InitTable {
    private String packageName = null;
    private String driverClassName = null;
    private String url = null;
    private String user = null;
    private String password = null;
    private boolean dropTable = false;

    private InitTable(String packageName, String driverClassName, String url, String user, String password) {
        this.packageName = packageName;
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private InitTable(String packageName, String driverClassName, String url, String user, String password,boolean dropTable) {
        this.packageName = packageName;
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.dropTable = dropTable;
    }

    public static InitTable getInstance(String packageName, String driverClassName, String url, String user, String password){
        return new InitTable(packageName, driverClassName, url, user, password);
    }
    public static InitTable getInstance(String packageName, String driverClassName, String url, String user, String password,boolean dropTable){
        return new InitTable(packageName, driverClassName, url, user, password,dropTable);
    }

    public boolean init() throws ClassNotFoundException, SQLException {
        Long start = System.currentTimeMillis();

        //scan annotation
        Set<Class<?>> classSet = EntityScan.getClassSet(packageName);

        //get annotation msg
        TableMsg[] array = EntityScan.getTableMsgs(classSet);

        SQLRunner sqlRunner = new SQLRunner();

        boolean tableIsExist = sqlRunner.tableIsExist(array, driverClassName, url, user, password);
        if(tableIsExist && !dropTable){
            //if the table is existed, and does not drop the tables is return
            return false;
        }

        createTables(array, sqlRunner);

        System.out.println(System.currentTimeMillis() - start + "ms ");
        return true;
    }

    private void createTables(TableMsg[] array, SQLRunner sqlRunner) throws ClassNotFoundException, SQLException {
        SQLGenerated sqlGenerated = new SQLFactory().createSQLGenerated(driverClassName);
        List<String> allSQL = new ArrayList<>();
        if(dropTable){
            //get drop tables sql;
            String dropSQL = sqlGenerated.dropTableSQL(array);
            allSQL.add(dropSQL);
        }

        // get create table sqls!
        allSQL.addAll(sqlGenerated.createTableSQL(array));

        //write sql to databases
        sqlRunner.executeStatement(allSQL.toArray(new String[allSQL.size()]),driverClassName,url,user,password);
    }

}
