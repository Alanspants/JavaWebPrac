package com.bjpowernode.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert命令推送流程 {

    public static void main(String[] args) throws SQLException {
        // 货物
        String sql = "insert into student values(2,'oj', 'm')";
        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        // JDBC 步骤1：建立链接通道
        Connection con = DriverManager.getConnection(url, userName, password);

        // JDBC 步骤2：建立交通工具
        PreparedStatement ps = con.prepareStatement(sql);

        // JDBC 步骤3：通信
        // DML insert, update, delete -> 返回生效的数据行
        int line = ps.executeUpdate();
        System.out.println("本次插入了" + line + "数据");

        // JDBC 步骤4：销毁资源
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
