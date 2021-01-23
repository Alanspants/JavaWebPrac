package com.bjpowernode.dao;

import com.bjpowernode.entity.Dept;
import com.bjpowernode.util.JdbcUtil;
import com.bjpowernode.util.ReflectUtil;

public class DeptDao {

    public int deleteDept(Integer deptNo){
        Dept dept = new Dept();
        dept.setDeptNo(deptNo);
        String sql = ReflectUtil.createDelete(dept, "deptNo");
        int flag = 0;
        try {
            JdbcUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JdbcUtil.closePreparedStatement();
        }
        return flag;
    }
}
