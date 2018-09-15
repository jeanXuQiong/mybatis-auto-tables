package com.github.jeanXuQiong.util.tablemsg;

import java.util.*;

public class MysqlSQLGenerated implements SQLGenerated {

    private static MysqlSQLGenerated mysqlSQLGenerated;

    private String autoIncrementField = null;

    private MysqlSQLGenerated() {
    }

    public static MysqlSQLGenerated getInstance() {
        synchronized (MysqlSQLGenerated.class) {
            if (null == mysqlSQLGenerated) {
                mysqlSQLGenerated = new MysqlSQLGenerated();
            }
        }
        return mysqlSQLGenerated;
    }

    public List<String> createTableSQL(TableMsg[] array) {
        StringBuilder sb = new StringBuilder();
        List<String> sqls = new ArrayList<>();

        for (TableMsg table : array) {
            sb.setLength(0);
            sb.append(this.createTableSQL(table));
            sqls.add(sb.toString());
        }
        return sqls;
    }

    @Override
    public String dropTableSQL(TableMsg[] array) {
        List<String> sqls = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE IF EXISTS ");

        boolean flag = false;
        for (TableMsg table : array) {
            if (flag) {
                sb.append(",");
            }

            flag = true;
            sb.append("`" + table.getValue() + "`");
        }
        sb.append(";");
            
        return sb.toString();
    }

    private StringBuilder createTableSQL(TableMsg table) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> primaryKeyMap = new HashMap<>();
        sb.append("CREATE TABLE `" + table.getValue() + "` (");

        List<FieldMsg> fields = table.getFields();
        sb = createFieldSQL(sb, primaryKeyMap, fields);

        //主键处理
        if (primaryKeyMap.size() > 0) {
            sb = putKeyToSql(sb, primaryKeyMap);
        }
        sb.append(")");
        sb.append("DEFAULT CHARACTER SET=" + table.getCharcter());
        sb.append(";");
        return sb;
    }

    private StringBuilder createFieldSQL(StringBuilder sb, Map<String, Integer> primaryKeyMap, List<FieldMsg> fields) {
        for (int i = 0; i < fields.size(); i++) {
            FieldMsg field = fields.get(i);
            if (i > 0) {
                sb.append(",");
            }
            sb.append("`" + field.getName() + "`  ");
                switch (MySQLType.getByValue(field.getType())) {
                    case BIGINT :
                        sb = haveUnsignedOrZerofillOrAutoIncrement(sb, field);
                        break;
                }
            if (FieldType.BIGINT.equals(field.getType()) ||
                    FieldType.TINYINT.equals(field.getType()) ||
                    FieldType.SMALLINT.equals(field.getType()) ||
                    FieldType.MEDIUMINT.equals(field.getType()) ||
                    FieldType.INT.equals(field.getType()) ||
                    FieldType.INTEGER.equals(field.getType())) {
                //默认 注释 无符号 填充0 长度  自动递增
                sb = haveUnsignedOrZerofillOrAutoIncrement(sb, field);

            } else if (FieldType.REAL.equals(field.getType()) ||
                    FieldType.DOUBLE.equals(field.getType()) ||
                    FieldType.FLOAT.equals(field.getType())) {
                //默认 注释 无符号 填充0 长度  小数 自动递增
                sb = haveUnsignedOrZerofillOrAutoIncrementAndPoint(sb, field);

            } else if (FieldType.DECIMAL.equals(field.getType()) ||
                    FieldType.NUMERIC.equals(field.getType())) {
                //默认 注释 无符号 填充0 长度  小数
                sb = haveUnsignedOrZerofillAndPoint(sb, field);

            } else if (FieldType.CHAR.equals(field.getType()) ||
                    FieldType.VARCHAR.equals(field.getType())) {
                //默认 注释   字符 排序 键长度 长度
                sb = haveCharcterAndCollateAndLength(sb, field);

            } else if (FieldType.TINYTEXT.equals(field.getType()) ||
                    FieldType.MEDIUMTEXT.equals(field.getType()) ||
                    FieldType.LONGTEXT.equals(field.getType())) {
                //默认 注释   字符 排序 键长度 （无长度）
                sb = haveCharcterAndCollateAndNoLength(sb, field);

            } else if (FieldType.SET.equals(field.getType()) ||
                    FieldType.ENUM.equals(field.getType())) {
                //默认 注释  值  字符 排序 （无长度）
                //指定
                sb = getSetAndEnumSql(sb, field);

            } else if (FieldType.TIMESTAMP.equals(field.getType())) {
                //默认  注释（无长度） 时间戳
                sb = haveTimeStamp(sb, field);

            } else if (FieldType.TIME.equals(field.getType()) ||
                    FieldType.DATETIME.equals(field.getType())) {
                //默认  注释（有长度）
                sb = haveLength(sb, field);

            } else if (FieldType.BIT.equals(field.getType()) ||
                    FieldType.DATE.equals(field.getType()) ||
                    FieldType.YEAR.equals(field.getType()) ||
                    FieldType.BINARY.equals(field.getType()) ||
                    FieldType.VARBINARY.equals(field.getType()) ||
                    FieldType.POINT.equals(field.getType()) ||
                    FieldType.LINESTRING.equals(field.getType()) ||
                    FieldType.POLYGON.equals(field.getType()) ||
                    FieldType.GEOMETRY.equals(field.getType()) ||
                    FieldType.MULTIPOINT.equals(field.getType()) ||
                    FieldType.MULTILINESTRING.equals(field.getType()) ||
                    FieldType.MULTIPOLYGON.equals(field.getType()) ||
                    FieldType.GEOMETRYCOLLECTION.equals(field.getType()) ||
                    FieldType.TINYBLOB.equals(field.getType()) ||
                    FieldType.BLOB.equals(field.getType()) ||
                    FieldType.MEDIUMBLOB.equals(field.getType()) ||
                    FieldType.LONGBLOB.equals(field.getType())) {
                //默认  注释（无长度）
                sb = getSqlWithNoLength(sb, field);
            }

            if (field.isPrimaryKey()) {
                primaryKeyMap.put(field.getName(), field.getPrimaryKeyLength());

                if (field.isAutoIncrement()) {
                    autoIncrementField = field.getName();
                }
            }
        }
        return sb;
    }

    private StringBuilder putKeyToSql(StringBuilder sb, Map<String, Integer> primaryKeyMap) {
        sb.append(",PRIMARY KEY (");
        int pc = 0;

        if (null != autoIncrementField) {
            Integer integer = primaryKeyMap.get(autoIncrementField);

            getPrimaryKeyMsg(sb, autoIncrementField, integer);
            primaryKeyMap.remove(autoIncrementField);
            autoIncrementField = null;

            pc++;
        }



        for (Map.Entry<String, Integer> entry : primaryKeyMap.entrySet()) {
            if (pc > 0) {
                sb.append(",");
            }
            pc++;
            Integer length = entry.getValue();
            String name = entry.getKey();

            getPrimaryKeyMsg(sb, name, length);
        }

        sb.append(")");
        return sb;
    }

    private void getPrimaryKeyMsg(StringBuilder sb, String name, Integer length) {
        if (0 < length) {
            sb.append("`" + name + "`(" + length + ")");
        } else {
            sb.append("`" + name + "`");
        }
    }

    private StringBuilder getSqlWithNoLength(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + " ");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder haveLength(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + "(" + field.getLength() + ") ");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder haveTimeStamp(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + " ");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(field.isCurrentTimestamp() ? "ON UPDATE CURRENT_TIMESTAMP " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder getSetAndEnumSql(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + "(");
        if (field.getSpecifiedValues().length == 0) {
            sb.append("");
        } else {
            for (int j = 0; j < field.getSpecifiedValues().length; j++) {
                if (j > 0) {
                    sb.append(",");
                }
                sb.append("'" + field.getSpecifiedValues()[j] + "'");
            }
        }
        sb.append(")");

        sb.append("CHARACTER SET " + field.getCharcter() + " ");
        sb.append("COLLATE " + field.getDefaultCollation() + " ");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder haveCharcterAndCollateAndNoLength(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + " ");
        sb.append("CHARACTER SET " + field.getCharcter() + " ");
        sb.append("COLLATE " + field.getDefaultCollation() + " ");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder haveCharcterAndCollateAndLength(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + "(" + field.getLength() + ") ");
        sb.append("CHARACTER SET " + field.getCharcter() + " ");
        sb.append("COLLATE " + field.getDefaultCollation() + " ");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT '" + field.getDefaultValue() + "' " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private String fieldComments(FieldMsg field) {
        return null != field.getComments() ? "COMMENT '" + field.getComments() + "' " : "";
    }

    private StringBuilder haveUnsignedOrZerofillAndPoint(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + "(" + field.getLength() + "," + field.getDecimalPoint() + ") ");
        sb.append(field.isUnsigned() ? "UNSIGNED " : "");
        sb.append(field.isZeroFill() ? "ZEROFILL " : "");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder haveUnsignedOrZerofillOrAutoIncrementAndPoint(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + "(" + field.getLength() + "," + field.getDecimalPoint() + ") ");
        sb.append(field.isUnsigned() ? "UNSIGNED " : "");
        sb.append(field.isZeroFill() ? "ZEROFILL " : "");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        if (field.isPrimaryKey()) {
            sb.append(field.isAutoIncrement() ? "AUTO_INCREMENT " : "");
        }
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

    private StringBuilder haveUnsignedOrZerofillOrAutoIncrement(StringBuilder sb, FieldMsg field) {
        sb.append(field.getType() + "(" + field.getLength() + ") ");
        sb.append(field.isUnsigned() ? "UNSIGNED " : "");
        sb.append(field.isZeroFill() ? "ZEROFILL " : "");
        sb.append((field.isNotNull() ? "NOT" : "") + " NULL ");
        if (field.isPrimaryKey()) {
            sb.append(field.isAutoIncrement() ? "AUTO_INCREMENT " : "");
        }
        sb.append(null != field.getDefaultValue() ? "DEFAULT " + field.getDefaultValue() + " " : "");
        sb.append(fieldComments(field));
        return sb;
    }

}
