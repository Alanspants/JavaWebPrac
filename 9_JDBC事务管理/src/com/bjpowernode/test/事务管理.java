package com.bjpowernode.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class 事务管理 {

    public static void main(String[] args) throws SQLException {

        String sql1 = "delete from student where sid = 50";
        String sql2 = "delete from student where sid = 51";

        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        Connection con = DriverManager.getConnection(url, "root", "990518Chz");

        // 抢夺当前事务管理对象管理权
        // 通知mysql服务器，接下来由这个通道推送的sql命令都要由同一个事务对象管理
        // 这个事务管理对象是否进行提交/回滚，由当前con进行负责
        con.setAutoCommit(false); // start transaction;

        PreparedStatement ps = con.prepareStatement(sql1); //创建一辆空车
        ps.addBatch(sql1);
        ps.addBatch(sql2);
        try {
            ps.executeBatch();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }

}
