package com.bjpowernode.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcUtil {

    Connection con = null;
    PreparedStatement ps = null;

    //---------------------------------------------------------------------------------------

    public Connection getCon(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        Map map = (ConcurrentHashMap) application.getAttribute("conPool");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            con = (Connection) it.next();
            boolean flag = (boolean) map.get(con);
            if (flag == true) {
                map.put(con, false);
                return con;
            }
        }
        return null;
    }

    public PreparedStatement getPs(String sql, HttpServletRequest request){
        try {
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    public void close(HttpServletRequest request) {

        ServletContext application = null;

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        // 将使用过的Connection设置为空闲状态
        application = request.getServletContext();
        Map map = (ConcurrentHashMap) application.getAttribute("conPool");
        map.put(con, true);

    }

    //---------------------------------------------------------------------------------------

    // 简化获得链接通道难度
    public Connection getCon() {
        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    // 简化获得交通工具难度
    public PreparedStatement getPs(String sql) {

        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    // 简化PreparedStatement & Connection 销毁难度
    public void close() {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        close();
    }
}
