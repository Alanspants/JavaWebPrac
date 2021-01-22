package com.bjpowernode.controller;

import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Emp;
import com.bjpowernode.util.ReflectUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmpAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Emp emp = null;
        EmpDao dao = new EmpDao();

        // 1. 将浏览器发送请求内容赋值给emp对象
        emp = (Emp) ReflectUtil.init(Emp.class, request);
        System.out.println(emp.toString());

        // 3. 调用dao实现插入操作
        dao.insert(emp);

        // 4. 响应
    }
}
