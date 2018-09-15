package com.github.jeanXuQiong.util.tablemsg;

import com.github.jeanXuQiong.util.tablemsg.annotation.Field;
import com.github.jeanXuQiong.util.tablemsg.annotation.Table;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class EntityScan {
    /**
     * 加载指定包下的所有类
     *
     * @param @param  packageName
     * @param @return 设定文件
     * @return Set<Class<?>>    返回类型
     * @throws
     * @Title: getClassSet
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        URL resource = getClassLoader().getResource(packageName.replace(".", "/"));

        if (!StringUtils.isNotNull(packageName) || packageName.trim().equals("/")) {
            packageName = "";
        }

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {

                URL url = urls.nextElement();

                if (url != null) {

                    String protocol = url.getProtocol();

                    if (protocol.equals("file")) {
                        // 转码
                        String packagePath = URLDecoder.decode(url.getFile(), "UTF-8");
                        // String packagePath =url.getPath().replaceAll("%20",
                        // "");
                        // 添加
                        addClass(classSet, packagePath, packageName);

                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();

                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();

                            if (jarFile != null) {

                                Enumeration<JarEntry> jarEntries = jarFile.entries();

                                while (jarEntries.hasMoreElements()) {

                                    JarEntry jarEntry = jarEntries.nextElement();

                                    String jarEntryName = jarEntry.getName();

                                    if (jarEntryName.endsWith(".class")) {

                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
                                                .replaceAll("/", ".");
                                        doAddClass(classSet, className);

                                    }
                                }

                            }
                        }
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
//			LOGGER.error("查找包下的类失败{}", e);
        }

        return classSet;
    }

    /**
     * 添加文件到SET集合
     *
     * @param @param classSet
     * @param @param packagePath
     * @param @param packageName    设定文件
     * @return void    返回类型
     * @throws
     * @Title: addClass
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

        File[] files = new File(packagePath).listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                System.out.println(file.getName() + " :fileName!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
            }
        });

        for (File file : files) {

            String fileName = file.getName();

            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));

                if (StringUtils.isNotNull(packageName)) {

                    className = packageName + "." + className;
                }
                // 添加
                doAddClass(classSet, className);
            } else {
                // 子目录
                String subPackagePath = fileName;
                if (StringUtils.isNotNull(packageName)) {
                    if (packagePath.endsWith("/")) {
                        subPackagePath = packagePath.trim() + subPackagePath;
                    } else {
                        subPackagePath = packagePath.trim() + "/" + subPackagePath;
                    }
                }

                String subPackageName = fileName;
                if (StringUtils.isNotNull(packageName) && !packageName.equals("")) {
                    subPackageName = packageName.trim() + "." + subPackageName;
                }

                addClass(classSet, subPackagePath, subPackageName);
            }
        }

    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {

        Class<?> cls = loadClass(className, false);

        Table annotation = cls.getAnnotation(Table.class);

        if (null != annotation) {
            classSet.add(cls);
        } else {
            System.out.println(cls.getName() + " is not have Table annotation");
        }
    }

    /**
     * 获取类加载器
     *
     * @param @return 设定文件
     * @return ClassLoader    返回类型
     * @throws
     * @Title: getClassLoader
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public static ClassLoader getClassLoader() {

        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * 需要提供类名与是否初始化的标志，
     * 初始化是指是否执行静态代码块
     *
     * @param @param  className
     * @param @param  isInitialized  为提高性能设置为false
     * @param @return 设定文件
     * @return Class<?>    返回类型
     * @throws
     * @Title: loadClass
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {

        Class<?> cls;
        try {
            System.out.println(className);
            cls = Class.forName(className, isInitialized, getClassLoader());
            //Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return cls;
    }

    public static TableMsg[] getTableMsgs(Set<Class<?>> classSet) {
        Set<TableMsg> array = new HashSet<>();
        for (Class<?> clazz : classSet) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);

            TableMsg table = new TableMsg();
            table.setValue(StringUtils.isNotBlank(tableAnnotation.value()) ? tableAnnotation.value() : clazz.getTypeName());
            table.setCharcter(StringUtils.isNotBlank(tableAnnotation.charcter()) ? tableAnnotation.charcter() : clazz.getTypeName());
            array.add(table);

            ArrayList<FieldMsg> fieldArray = new ArrayList<>();
            java.lang.reflect.Field[] declaredFields = clazz.getDeclaredFields();
            table.setFields(fieldArray);
            for (java.lang.reflect.Field field1 : declaredFields) {

                Field annotation1 = field1.getAnnotation(Field.class);
                if (null != annotation1) {
                    FieldMsg field = new FieldMsg();
                    fieldArray.add(field);

                    field.setName(StringUtils.isNotBlank(annotation1.value()) ? annotation1.value() : field1.getName());
                    field.setCharcter(annotation1.charcter());
                    field.setComments(StringUtils.isNotBlank(annotation1.comments()) ? annotation1.comments() : null);
                    field.setDecimalPoint(annotation1.decimalPoint());
                    field.setDefaultCollation(annotation1.defaultCollation());
                    field.setDefaultValue(StringUtils.isNotBlank(annotation1.defaultValue()) ? annotation1.defaultValue() : null);
                    field.setLength(annotation1.length());
                    field.setNotNull(annotation1.notNull());
                    field.setType(annotation1.type());
                    field.setAutoIncrement(annotation1.isAutoIncrement());
                    field.setPrimaryKey(annotation1.isPrimaryKey());
                    field.setPrimaryKeyLength(annotation1.primaryKeyLength());
                    field.setSpecifiedValues(annotation1.specifiedValues());
                    field.setCurrentTimestamp(annotation1.currentTimestamp());
                    field.setUnsigned(annotation1.isUnsigned());
                    field.setZeroFill(annotation1.isZeroFill());
                }
            }
        }

        return array.toArray(new TableMsg[array.size()]);
    }
}
