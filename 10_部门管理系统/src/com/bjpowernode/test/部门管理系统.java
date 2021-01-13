package com.bjpowernode.test;

import java.sql.*;
import java.util.Scanner;

public class 部门管理系统 {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        int flag = 0;

        String deptNo, dname, loc;

        String insert_sql = "insert into dept values(?,?,?)";
        String select_sql = "select * from dept";
        String delete_sql = "delete from dept where deptNo = ?";
        String update_sql = "update dept set dname = ?, loc = ? where deptNo = ?";

        // 操作界面
        System.out.println("***** 1. 部门注册 *****");
        System.out.println("***** 2. 部门查询 *****");
        System.out.println("***** 3. 部门删除 *****");
        System.out.println("***** 4. 部门更新 *****");
        System.out.println("请选择");
        flag = sc.nextInt();

        if (flag == 1) {
            System.out.println("请输入新部门编号");
            deptNo = sc.next();
            System.out.println("请输入新部门名称");
            dname = sc.next();
            System.out.println("请输入新部门位置");
            loc = sc.next();

            String userName = "root";
            String password = "990518Chz";
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            Connection con = DriverManager.getConnection(url, userName, password);

            PreparedStatement ps = con.prepareStatement(insert_sql);
            ps.setString(1, deptNo);
            ps.setString(2, dname);
            ps.setString(3, loc);
            flag = ps.executeUpdate();

            if (ps != null) ps.close();
            if (con != null) con.close();
            if (flag == 1) {
                System.out.println("新部门注册成功");
            } else {
                System.out.println("新部门注册失败");
            }

        } else if (flag == 2) {
            String userName = "root";
            String password = "990518Chz";
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            Connection con = DriverManager.getConnection(url, userName, password);

            PreparedStatement ps = con.prepareStatement(select_sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                deptNo = rs.getString("deptno");
                dname = rs.getString("dname");
                loc = rs.getString("loc");
                System.out.println("部门编号 " + deptNo + " 部门名称 " + dname + " 部门位置 " + loc);
            }

            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();

        } else if (flag == 3) {
            System.out.println("请输入需要删除的部门编号");
            deptNo = sc.next();
            String userName = "root";
            String password = "990518Chz";
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            Connection con = DriverManager.getConnection(url, userName, password);

            PreparedStatement ps = con.prepareStatement(delete_sql);
            ps.setString(1, deptNo);
            flag = ps.executeUpdate();

            if (ps != null) ps.close();
            if (con != null) con.close();

            if(flag == 1){
                System.out.println("部门" + deptNo + "被成功删除");
            } else{
                System.out.println("部门" + deptNo + "无法删除");
            }

        } else if (flag == 4) {
            System.out.println("请输入部门编号");
            deptNo = sc.next();
            System.out.println("请输入新部门名称");
            dname = sc.next();
            System.out.println("请输入新部门位置");
            loc = sc.next();

            String userName = "root";
            String password = "990518Chz";
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            Connection con = DriverManager.getConnection(url, userName, password);

            PreparedStatement ps = con.prepareStatement(update_sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptNo);
            flag = ps.executeUpdate();

            if (ps != null) ps.close();
            if (con != null) con.close();

            if(flag == 1){
                System.out.println("部门" + deptNo + "被成功更新");
            } else{
                System.out.println("部门" + deptNo + "无法更新");
            }
        }

    }
}
