package com.bjpowernode.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptDao {

    // 封装插入操作实现细节
    public int insert(String deptNo, String dname, String loc) {
        String sql = "insert into dept values(?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int flag = 0;

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            con = DriverManager.getConnection(url, userName, password);
            ps = con.prepareStatement(sql);
            ps.setString(1, deptNo);
            ps.setString(2, dname);
            ps.setString(3, loc);
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return flag;
    }

    //封装删除操作流程
    public int delete(String deptNo) {
        String sql = "delete from dept where deptNo = ?";
        Connection con = null;
        PreparedStatement ps = null;
        int flag = 0;

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            con = DriverManager.getConnection(url, userName, password);
            ps = con.prepareStatement(sql);
            ps.setString(1, deptNo);
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
        return flag;
    }

    //封装更新操作流程
    public int update(String deptNo, String dname, String loc){
        String sql = "update dept set dname = ?, loc = ? where deptno = ?";
        Connection con = null;
        PreparedStatement ps = null;
        int flag = 0;

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            con = DriverManager.getConnection(url, userName, password);
            ps = con.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptNo);
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return flag;
    }
}
