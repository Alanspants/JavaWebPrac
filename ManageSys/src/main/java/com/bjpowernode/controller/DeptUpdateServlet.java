package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dname, loc, deptNo;
        DeptDao dao = new DeptDao();
        Dept dept = null;
        int flag = 0;
        PrintWriter out = null;

        // 1. 调用请求对象读取请求头中参数信息，得到需要更新的部门信息
        dname = request.getParameter("dname");
        loc = request.getParameter("loc");
        deptNo = request.getParameter("deptNo");

        // 2. 调用dao对象推送update命令
        dept = new Dept(Integer.valueOf(deptNo), dname, loc);
        flag = dao.update(dept);

        // 3. 调用响应对象，将处理结果写到响应体中
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if (flag == 1) {
            out.print("<center><font style='color:red;font-size:35px'>部门更新成功</font></center>");
        } else {
            out.print("<center><font style='color:red;font-size:35px'>部门更新失败</font></center>");
        }
    }
}
