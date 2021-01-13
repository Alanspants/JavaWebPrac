package com.bjpowernode.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class 部门管理系统 {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        int flag = 0;

        String deptNo, dname, loc;

        String insert_sql = "insert into dept values(?,?,?)";

        // 操作界面
        System.out.println("***** 1. 部门注册 *****");
        System.out.println("***** 1. 部门查询 *****");
        System.out.println("***** 1. 部门删除 *****");
        System.out.println("***** 1. 部门更新 *****");
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
            if(flag == 1){
                System.out.println("新部门注册成功");
            } else {
                System.out.println("新部门注册失败");
            }

        } else if (flag == 2) {

        } else if (flag == 3) {

        } else if (flag == 4) {

        }

    }
}
