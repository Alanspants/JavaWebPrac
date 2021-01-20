package com.bjpowernode.dao;

import com.bjpowernode.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDao {

    JdbcUtil util = new JdbcUtil();

    // login
    public int login(String username, String password) {
        ResultSet rs = null;
        int flag = 0;

        String sql = "select count(*) from emp where ename = ? and empno=?";
        PreparedStatement ps = util.getPs(sql);
        try {
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            flag = rs.getInt("count(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return flag;
    }
}
