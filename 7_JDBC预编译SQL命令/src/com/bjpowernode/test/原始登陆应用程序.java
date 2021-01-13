package com.bjpowernode.test;

import java.sql.*;
import java.util.Scanner;

public class 原始登陆应用程序 {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String userName = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();

        String sql = "select count(*) from student where sname = '" + userName + "' " + "and sid = " + password;

        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        Connection con = DriverManager.getConnection(url, "root", "990518Chz");

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int flag = rs.getInt("count(*)");
        if(flag == 0){
            System.out.println("登陆信息不存在，请重新登陆");
        } else {
            System.out.println("欢迎光临");
        }

        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
    }
}
