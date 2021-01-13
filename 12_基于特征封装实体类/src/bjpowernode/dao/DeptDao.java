package bjpowernode.dao;

import bjpowernode.entity.Dept;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {

    // 封装插入操作实现细节
    public int insert(Dept dept) {
        String sql = "insert into dept values(?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        int flag = 0;

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            con = DriverManager.getConnection(url, userName, password);
            ps = con.prepareStatement(sql);
            ps.setInt(1, dept.getDeptNo());
            ps.setString(2, dept.getDname());
            ps.setString(3, dept.getLoc());
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return flag;
    }

    //封装删除操作流程
    public int delete(String deptNo) {
        String sql = "delete from dept where deptNo = ?";
        Connection con = null;
        PreparedStatement ps = null;
        int flag = 0;

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            con = DriverManager.getConnection(url, userName, password);
            ps = con.prepareStatement(sql);
            ps.setString(1, deptNo);
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return flag;
    }

    //封装更新操作流程
    public int update(String deptNo, String dname, String loc) {
        String sql = "update dept set dname = ?, loc = ? where deptno = ?";
        Connection con = null;
        PreparedStatement ps = null;
        int flag = 0;

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            con = DriverManager.getConnection(url, userName, password);
            ps = con.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptNo);
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return flag;
    }

    //查询所有部门信息流程
    public List findAll() {
        String sql = "select * from dept";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList<Dept>();

        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            // 数据交接
            while(rs.next()){
                Integer deptNo = rs.getInt("deptNo");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept(deptNo, dname, loc);
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list;
    }
}
