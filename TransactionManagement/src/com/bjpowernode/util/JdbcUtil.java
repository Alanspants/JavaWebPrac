package com.bjpowernode.util;

import java.sql.*;

public class JdbcUtil {

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 创建Connection
    public static Connection createConnection() {
        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    // 创建交通工具
    public static PreparedStatement createPS() {
        try {
            ps = con.prepareStatement("test");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    // 推送DML命令 insert, update, delete
    public static int executeUpdate(String dml) throws Exception {
        if(ps == null){
            createPS();
        }
        int flag = ps.executeUpdate(dml);
        return flag;
    }

    // 推送DQL命令 select
    public static ResultSet executeQuery(String dql) throws Exception {
        rs = ps.executeQuery(dql);
        return rs;
    }

    // 销毁ResultSet
    public void closeResult() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // 销毁PreparedStatement
    public static void closePreparedStatement() {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                ps = null;
            }
        }
    }

    // 销毁Con
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static ResultSet getRs() {
        return rs;
    }
}
