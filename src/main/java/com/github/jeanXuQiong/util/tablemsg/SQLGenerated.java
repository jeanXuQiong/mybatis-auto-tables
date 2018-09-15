package com.github.jeanXuQiong.util.tablemsg;

import java.util.List;
import java.util.Set;

public interface SQLGenerated {

    public List<String> createTableSQL(TableMsg[] array);

    public String dropTableSQL(TableMsg[] array);
}
