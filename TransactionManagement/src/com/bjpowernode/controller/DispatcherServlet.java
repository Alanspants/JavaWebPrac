package com.bjpowernode.controller;

import com.bjpowernode.serviceImpl.DeptService;
import com.bjpowernode.util.JdbcUtil;

import java.sql.Connection;

public class DispatcherServlet {

    public static void main(String[] args) throws Exception{
        DeptService service = new DeptService();

        // ----------- 通过事物管理service业务方法执行 -----------------------
        Connection con = JdbcUtil.createConnection();
        con.setAutoCommit(false);
        try {
            service.deleteDept(10);
            con.commit();
        } catch (Exception ex) {
            con.rollback();
        } finally {
            JdbcUtil.closeConnection();
        }

        // ----------- 通过事物管理service业务方法执行 -----------------------

    }
}
