package com.github.jeanXuQiong.util.tablemsg;

import java.io.IOException;
import java.util.List;

public class TableMsg {

    private String value;
    private String charcter;

    private List<FieldMsg> fields;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCharcter() {
        return charcter;
    }

    public void setCharcter(String charcter) {
        this.charcter = charcter;
    }

    public List<FieldMsg> getFields() {
        return fields;
    }

    public void setFields(List<FieldMsg> fields) {
        this.fields = fields;
    }

    private static long time;
    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        time = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            builder = new StringBuilder();
            builder.append("aa");
            builder.append("bb");
            builder.append("cc");
            builder.append("dd");
            builder.append("ee");
        }
        System.out.println("new 耗时：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            builder.delete(0, builder.length());
            builder.append("a");
            builder.append("b");
            builder.append("c");
            builder.append("d");
            builder.append("e");
        }
        System.out.println("delete 耗时：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            builder.setLength(0);
            builder.append("1a");
            builder.append("1b");
            builder.append("1c");
            builder.append("1d");
            builder.append("1e");
        }
        System.out.println("setLenth=0 耗时：" + (System.currentTimeMillis() - time));
    }
}
