package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.entity.Emp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmpInitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeptDao deptDao = new DeptDao();
        EmpDao empDao = new EmpDao();

        // 1. 调用dao对象查询所有部门编号和名称 [DeptList]
        List<Dept> deptList = deptDao.findAll();

        // 2. 调用dao对象查询所有领导的编号何其相关的姓名 [EmpList]
        List<Emp> empList = empDao.findMgr();

        // 3. 将查询结果依次存入到请求作用域对象
        request.setAttribute("deptList", deptList);
        request.setAttribute("empList", empList);

        // 4. 通过请求转发方式向tomcat索要emp_add.jsp
        request.getRequestDispatcher("/emp_add.jsp").forward(request, response);
    }
}
