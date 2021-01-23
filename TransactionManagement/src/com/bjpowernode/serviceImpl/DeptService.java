package com.bjpowernode.serviceImpl;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.service.BaseService;

public class DeptService extends BaseService {
    // 封装一个部门删除业务
    // 分支任务1：将部门下职员删除
    // 分支任务2：将部门删除

    private DeptDao deptDao = new DeptDao();
    private EmpDao empDao = new EmpDao();

    /*
    * 原始事物管理代码
    * Connection con = getCon();
    * con.setAutoCommit(false); // start transaction
    * PrepareStatement ps1 = con.preparedStatement("delete from emp where deptno = ?");
    * PrepareStatement ps2 = con.preparedStatement("delete from dept where deptno = ?");
    * try {
    *   ps1.executeUpdate();
    *   ps2.executeUpdate();
    *   con.commit;
    * } catch {SQLException ex) {
    *   con.rollback;
    * }
    *
    * 改良
    * Connection con = getCon();
    * con.setAutoCommit(false); // start transaction
    * try {
    *   deptService.deleteDept();
    *   con.commit;
    * } catch {SQLException ex) {
    *   con.rollback;
    * }
    */
    public String deleteDept(Integer deptNo) {
        empDao.deleteEmpByDeptNo(deptNo); // delete from emp where deptno = ?
        deptDao.deleteDept(deptNo);       // delete from dept where deptNo = ?
        return "成功";
    }

}
