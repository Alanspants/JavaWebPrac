package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeptFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeptDao dao = new DeptDao();
        PrintWriter out = null;

//        // 询问来访者身份
//        HttpSession session = request.getSession(false);
//        if(session == null){
//            request.getRequestDispatcher("/login_error.html").forward(request, response);
//            return;
//        }

        // 1. 调用dao对象推送查询命令 得到部门信息集合 (List<Dept>）
        List<Dept> deptList = dao.findAll();

        // 2. 调用响应对象将部门信息结合table标签写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        out.print("<script type='text/javascript' src='/myWeb/common.js'></script>");
        out.print("<center>");
        out.print("<table border='2'>");
        out.print("<tr>");
        out.print("<td><input type='checkbox' onclick='window.func1()'/>全选/全不选</td>");
        out.print("<td>部门编号</td>");
        out.print("<td>部门名称</td>");
        out.print("<td>部门位置</td>");
        out.print("<td colspan='2'>操作</td>");
        out.print("</tr>");
        for (Dept dept : deptList) {
            out.print("<tr>");
            out.print("<td><input type='checkbox'/></td>");
            out.print("<td>" + dept.getDeptNo() + "</td>");
            out.print("<td>" + dept.getDname() + "</td>");
            out.print("<td>" + dept.getLoc() + "</td>");
            out.print("<td><a href='/myWeb/dept/delete?deptNo=" + dept.getDeptNo() + "'>部门删除</a></td>");
            out.print("<td><a href='/myWeb/dept/findById?deptNo=" + dept.getDeptNo() + "'>详细信息</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
        out.print("</center>");
    }
}
