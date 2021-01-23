package com.bjpowernode.dao;

import com.bjpowernode.entity.Emp;
import com.bjpowernode.util.JdbcUtil;
import com.bjpowernode.util.ReflectUtil;

public class EmpDao {

    public int deleteEmpByDeptNo(Integer deptNo) {

        Emp emp = new Emp();
        emp.setDeptNo(deptNo);
        String sql = ReflectUtil.createDelete(emp, "deptNo");
        int flag = 0;
        try {
            JdbcUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JdbcUtil.closeConnection();
        }
        return flag;
    }

}
