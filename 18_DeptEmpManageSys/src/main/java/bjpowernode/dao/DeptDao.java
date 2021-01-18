package bjpowernode.dao;


import bjpowernode.entity.Dept;
import bjpowernode.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {

    JdbcUtil util = new JdbcUtil();

    // 推送insert
    public int insert(Dept dept) {
        String sql = "insert into dept (dname, loc) values (?, ?)";
        PreparedStatement ps = util.getPs(sql);
        int flag = 0;

        try {
            ps.setString(1, dept.getDname());
            ps.setString(2, dept.getLoc());
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }

        return flag;
    }

    // 查询所有部门信息
    public List<Dept> findAll() {
        String sql = "select * from dept";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        List<Dept> list = new ArrayList();

        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer deptNo = rs.getInt("deptNo");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept(deptNo, dname, loc);
                list.add(dept);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }

        return list;
    }

    // 推送delete
    public int delete(String deptNo) {
        String sql = "delete from dept where deptno = ?";
        PreparedStatement ps = util.getPs(sql);
        int flag = 0;

        try {
            ps.setString(1, deptNo);
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }

        return flag;
    }

    // findById
    public Dept findById(String deptNo) {
        String sql = "select * from dept where deptNo = ?";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        Dept dept = null;

        try {
            ps.setString(1, deptNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept = new Dept(Integer.valueOf(deptNo), dname, loc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }

        return dept;
    }

    // Update
    public int update(Dept dept) {
        String sql = "update dept set dname=?, loc=? where deptno=?";
        PreparedStatement ps = util.getPs(sql);
        int flag = 0;

        try {
            ps.setString(1, dept.getDname());
            ps.setString(2, dept.getLoc());
            ps.setInt(3, dept.getDeptNo());
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }

        return flag;
    }
}
