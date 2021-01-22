package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.entity.Emp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmpFindByIdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empNo = null;
        EmpDao dao = new EmpDao();
        Emp emp = null;
        DeptDao deptDao = new DeptDao();
        // 1. 调用请求参数获得职员编号
        empNo = request.getParameter("empNo");
        // 2. 调用dao对象查询职员信息
        emp = dao.findById(empNo);
        // 3. 获得所有领导信息
        List empList = dao.findMgr();
        // 4. 获得所有部门信息
        List deptList = deptDao.findAll();
        // 5. 调用emp_update.jsp展示职员信息
        request.setAttribute("empKey", emp);
        request.setAttribute("deptList", deptList);
        request.setAttribute("empList", empList);
        request.getRequestDispatcher("/emp_update.jsp").forward(request, response);
    }
}
