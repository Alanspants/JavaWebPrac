package com.bjpowernode.serviceImpl;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.BaseService;
import com.bjpowernode.util.ReflectUtil;

public class DeptService extends BaseService {

    private DeptDao dao;

    public String insertService() {
        Dept dept = (Dept) ReflectUtil.init(Dept.class, request);
        int flag = dao.insert(dept);
        if(flag == 1){
            request.setAttribute("info", "部门添加成功");
        } else {
            request.setAttribute("info", "部门添加失败");
        }
        return "/info.jsp";
    }
}
