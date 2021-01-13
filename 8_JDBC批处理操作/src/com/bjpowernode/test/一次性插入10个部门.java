package com.bjpowernode.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class 一次性插入10个部门 {
    public static void main(String[] args) throws SQLException {

        String sql = "insert into student values(?,?,?)";

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        Connection con = DriverManager.getConnection(url, userName, password);

        PreparedStatement ps = con.prepareStatement(sql);

        for (int i = 50; i <= 59; i++) {
            ps.setInt(1, i);
            ps.setString(2, "student_" + i);
            ps.setString(3, "F");
            // 将当前SQL作为子弹压入PrepareStatement
            ps.addBatch();
        }

        // 一次性将弹夹中的所有sql命令推动到SQL服务器执行
        ps.executeBatch();

        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
