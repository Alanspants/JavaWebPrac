package com.bjpowernode.service;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;

public class DeptService {
    // 封装部门注册业务解决方案
    public int deptInsert(Dept dept) {
        DeptDao dao = new DeptDao();
        int flag =  dao.insert(dept);
        return flag;
    }
}
