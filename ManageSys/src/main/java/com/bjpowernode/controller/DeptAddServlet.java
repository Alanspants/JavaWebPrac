package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dname, loc;
        DeptDao dao = new DeptDao();
        Dept dept = null;
        int flag = 0;
        PrintWriter out = null;

        // 1. 调用请求对象读取请求头参数，得到新部门信息
        dname = request.getParameter("dname");
        loc = request.getParameter("loc");

        // 2. 调用dao对象推送insert命令得到处理结果
        dept = new Dept(null, dname, loc);
        flag = dao.insert(dept);

        // 3. 调用响应对象将处理结果写入到响应体中
//        response.setContentType("text/html;charset=utf-8");
//        out = response.getWriter();
//        if(flag == 1){
//            out.print("<center><font style='color:red;font-size:35px'>部门插入成功</font></center>");
//        } else {
//            out.print("<center><font style='color:red;font-size:35px'>部门插入失败</font></center>");
//        }

        // 3. 重定向方案
        response.sendRedirect("/myWeb/dept/find");
    }
}
