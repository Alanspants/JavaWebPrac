package com.bjpowernode.dao;

import com.bjpowernode.entity.Emp;
import com.bjpowernode.util.JdbcUtil;
import com.bjpowernode.util.ReflectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpDao {

    JdbcUtil util = new JdbcUtil();

    // login
    public int login(String username, String password) {
        ResultSet rs = null;
        int flag = 0;

        String sql = "select count(*) from emp where ename = ? and empno=?";
        PreparedStatement ps = util.getPs(sql);
        try {
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            flag = rs.getInt("count(*)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return flag;
    }

    // insert
    public int insert(Emp emp) {
        int flag = 0;
        String sql = ReflectUtil.createInsert(emp);
        PreparedStatement ps = util.getPs(sql);
        try {
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return flag;
    }

    // find all
    public List<Emp> findAll() {
        String sql = "select * from emp";
        ResultSet rs = null;
        List list = null;

        PreparedStatement ps = util.getPs(sql);
        try {
            rs = ps.executeQuery();
            list = ReflectUtil.getData(rs, Emp.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        //将临时表数据行信息交给实体类对象保存
        return list;
    }

    // 查询领导编号和相关姓名
    public List<Emp> findMgr() {
        String sql = "select distinct emp.empno as empno, emp.ename as ename from emp " +
                "join (" +
                "    select distinct mgr from emp" +
                "    where mgr is not null" +
                "    ) as t1 " +
                "on emp.empno = t1.mgr or emp.mgr is null";

        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        List empList = null;
        try {
            rs = ps.executeQuery();
            empList = ReflectUtil.getData(rs, Emp.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return empList;
    }

    // find by id
    public Emp findById(String empNo) {
        String sql = "select * from emp where empNo = ?";
        ResultSet rs = null;
        List list = null;

        PreparedStatement ps = util.getPs(sql);
        try {
            ps.setString(1, empNo);
            rs = ps.executeQuery();
            list = ReflectUtil.getData(rs, Emp.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }

        return (Emp) list.get(0);
    }
}
