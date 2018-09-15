package com.github.jeanXuQiong.util.tablemsg;

public class SQLFactory {
    //mysql
    private static final String MYSQL_JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";
    //Oracle
    private static final String ORACLE_JDBC_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    //DB2
    private static final String DB2_JDBC_DRIVER_NAME = "com.ibm.db2.jcc.DB2Driver";
    //HSQLDB
    private static final String HSQLDB_JDBC_DRIVER_NAME = "org.hsqldb.jdbcDriver";
    //PostgreSQL
    private static final String POSTGRESQL_JDBC_DRIVER_NAME = "org.postgresql.Driver";
    //ODBC
    private static final String ODBC_JDBC_DRIVER_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    //MS SQL Server 2000 (Microsoft)
    private static final String SQLSERVER_MS_JDBC_DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //MS SQL Server 2000 (JTDS)
    private static final String SQLSERVER_JIDS_JDBC_DRIVER_NAME = "net.sourceforge.jtds.jdbc.Driver";
    

    public SQLGenerated createSQLGenerated(String driverClassName) {
        
        switch (driverClassName) {
            case MYSQL_JDBC_DRIVER_NAME:
                return MysqlSQLGenerated.getInstance();
            case ORACLE_JDBC_DRIVER_NAME:
                //TODO
                return null;
            case DB2_JDBC_DRIVER_NAME:
                //TODO
                return null;
            case HSQLDB_JDBC_DRIVER_NAME:
                //TODO
                return null;
            case POSTGRESQL_JDBC_DRIVER_NAME:
                //TODO
                return null;
            case ODBC_JDBC_DRIVER_NAME:
                //TODO
                return null;
            case SQLSERVER_MS_JDBC_DRIVER_NAME:
                //TODO
                return null;
            case SQLSERVER_JIDS_JDBC_DRIVER_NAME:
                //TODO
                return null;
            default:
                return null;
        }
    }
}
