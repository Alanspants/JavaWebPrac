package com.bjpowernode.test;

import java.sql.*;

public class Select命令推送流程 {

    public static void main(String[] args) throws SQLException {
        String sql = "select * from student";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        String userName = "root";
        String password = "990518Chz";

        // Step 1
        Connection con = DriverManager.getConnection(url, userName, password);

        // Step 2
        PreparedStatement ps = con.prepareStatement(sql);

        // Step 3
        ResultSet rs = ps.executeQuery(sql);

        // 获得当前临时表字段的个数和名称
        ResultSetMetaData rsmd = rs.getMetaData();
        int cc = rsmd.getColumnCount();
        for (int i = 1; i <= cc; i++) {
            System.out.println("字段名称" + rsmd.getColumnName(i));
        }

        // 遍历临时表所有数据行信息
        while(rs.next()){
            System.out.println("sid: " + rs.getInt("sid"));
            System.out.println("sname: " + rs.getString("sname"));
            System.out.println("sex: " + rs.getString("sex"));
        }

        // Step 4
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
    }
}
