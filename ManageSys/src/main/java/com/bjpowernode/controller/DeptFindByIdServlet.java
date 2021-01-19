package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptFindByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptNo = null;
        DeptDao dao = new DeptDao();
        Dept dept = null;
        PrintWriter out = null;

        // 1. 调用请求对象读取请求头参数，得到部门编号
        deptNo = request.getParameter("deptNo");

        // 2. 调用dao对象查询当前部门详细信息
        dept = dao.findById(deptNo);

        // 3. 调用响应对象将部门详细信息结合table标签写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        out.print("<center>");
        out.print("<form action='/myWeb/dept/update'>");
        out.print("<table border='2'>");
        out.print("<tr>");
        out.print("<td>部门编号</td>");
        out.print("<td><input type='text' name='deptNo' readOnly value='" + dept.getDeptNo() + "'></td>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<td>部门名称</td>");
        out.print("<td><input type='text' name='dname' value='" + dept.getDname() + "'></td>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<td>部门位置</td>");
        out.print("<td><input type='text' name='loc' value='" + dept.getLoc() + "'></td>");
        out.print("</tr>");
        out.print("<tr>");
        out.print("<td><input type='submit' value='更新部门'></td>");
        out.print("<td><input type='reset' value='重置'></td>");
        out.print("</tr>");
        out.print("</table>");
        out.print("</form>");
        out.print("</center>");
    }
}
