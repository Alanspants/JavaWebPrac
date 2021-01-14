package bjpowernode.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {

    Connection con = null;
    PreparedStatement ps = null;

    // 简化获得链接通道难度
    public Connection getCon() {
        String userName = "root";
        String password = "990518Chz";
        String url = "jdbc:mysql://localhost:3306/bjpowernode";
        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    // 简化获得交通工具难度
    public PreparedStatement getPs(String sql) {

        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    // 简化PreparedStatement & Connection 销毁难度
    public void close() {
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
}
