package com.bjpowernode.test;

import com.bjpowernode.dao.DeptDao;

import java.util.Scanner;

public class 部门管理应用程序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        String deptNo, dname, loc;

        DeptDao dao = new DeptDao();

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
            flag = dao.insert(deptNo, dname, loc);
            if (flag == 1) {
                System.out.println("新部门注册成功");
            } else {
                System.out.println("新部门注册失败");
            }
        } else if (flag == 2) {

        } else if (flag == 3) {
            System.out.println("请输入需要删除的部门编号");
            deptNo = sc.next();
            dao.delete(deptNo);

        } else if (flag == 4) {

        }

    }
}
