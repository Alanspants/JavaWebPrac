package com.bjpowernode.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

    /**
     * 自动读取请求包中参数的值并自动赋值给指定类型对象中属性
     * <p>
     * 问题1：如何得到请求参数名
     * 问题2：读取内容应该复制给对象中哪一个属性
     * <p>
     * 规则：要求请求参数名必须与当前实体类属性名相同，包括英文字母大小写
     */

    public static Object init(Class classFile, HttpServletRequest request) {

        Field fieldArray[] = null;
        Object instance = null;

        // 1. 获得当前类文件中所有的属性信息，变相得到本次请求中所有参数名
        fieldArray = classFile.getDeclaredFields();

        try {
            // 2. 创建当前实体类的实例对象
            instance = classFile.newInstance();

            // 3. 将请求参数内容赋值给对象中同名属性
            for (Field field : fieldArray) {
                String fieldName = field.getName(); //读取当前属性名称，变相得到请求参数名称
                String typeName = field.getType().getSimpleName(); // 读取当前属性关联的数据类型名称
                String value = request.getParameter(fieldName);
                field.setAccessible(true);
                if (value != null && !"".equals(value)) {
                    if (typeName.equals("Integer")) field.set(instance, Integer.valueOf(value));
                    else if (typeName.equals("Double")) field.set(instance, Double.valueOf(value));
                    else if (typeName.equals("String")) field.set(instance, value);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 根据得到实体类对象内容自动生成一个insert语句
     * insert into    表名    (字段名1，字段名2）   values（值1， 值2）
     * -----------    ---    -----------------   -----------------
     * 1          2             3                  4
     * <p>
     * 1. 如何得到操作表文件名：实体类的类名即为表名                dept.frm -> Dept.class
     * 2. 如何得到需要赋值的字段名：实体类属性名即为字段名           deptNo int -> private Integer deptNo
     * 3. 如何得到需要赋值的内容
     */
    public static String createInsert(Object instance) {

        StringBuffer sql = new StringBuffer("insert into ");
        StringBuffer columns = new StringBuffer("(");
        StringBuffer values = new StringBuffer(" values(");

        Class classFile = null;
        String tableName = null;
        Field fieldArray[] = null;

        // 1. 得到要操作表文件名
        classFile = instance.getClass();
        tableName = classFile.getSimpleName();  //得到类文件简短名

        // 2. 得到需要赋值字段名
        fieldArray = classFile.getDeclaredFields();
        for (Field field : fieldArray) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null && !"".equals(value)) {
                if (!columns.toString().equals("(")) {
                    columns.append(",");
                    values.append(",");
                }
                columns.append(fieldName);
                values.append("'");
                values.append(value);
                values.append("'");
            }

        }
        columns.append(")");
        values.append(")");

        // 4. 整理
        sql.append(tableName);
        sql.append(columns);
        sql.append(values);
        System.out.println(sql.toString());
        return sql.toString();
    }

    /**
     * 自动将ResultSet中数据行相关字段内容复制到指定实体类对象中同名属性中
     */
    public static List getData(ResultSet rs, Class classFile) {

        ResultSetMetaData rsmd = null;
        List list = new ArrayList();
        Field fieldArray[] = classFile.getDeclaredFields();

        try {
            // 1. 得到临时表字段名称
            rsmd = rs.getMetaData();

            // 2. 遍历临时表中每一行数据并将字段里的内容复制出来给实体类对象
            while (rs.next()) {
                Object instance = classFile.newInstance();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i);  //empNo
                    String value = rs.getString(columnName);
//                    Field field = classFile.getDeclaredField(columnName);// private int empNo
                    if (value != null) {
                        for (int j = 0; j < fieldArray.length; j++) {
                            Field field = fieldArray[j];
                            if (field.getName().equalsIgnoreCase(columnName)) {
                                field.setAccessible(true);
                                String typeName = field.getType().getSimpleName();
                                if (typeName.equals("Integer")) {
                                    field.set(instance, Integer.valueOf(value));
                                } else if (typeName.equals("Double")) {
                                    field.set(instance, Double.valueOf(value));
                                } else if (typeName.equals("String")) {
                                    field.set(instance, value);
                                }
                            }
                        }
                    }
                }
                list.add(instance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}
