package com.bjpowernode.controller;

import com.bjpowernode.dao.EmpDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmpFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpDao dao = new EmpDao();
        // 1.调用dao对象查询所有的职员信息
        dao.findAll();
        // 2.调用响应对象将职员信息结合table写入到响应体
        List list = dao.findAll();

        request.setAttribute("key", list);
        request.getRequestDispatcher("/emp_show.jsp").forward(request, response);
    }
}
