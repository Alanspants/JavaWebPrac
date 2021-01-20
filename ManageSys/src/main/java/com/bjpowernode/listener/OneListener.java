package com.bjpowernode.listener;

import com.bjpowernode.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OneListener implements ServletContextListener {

    // 预先创建一定数量的连接通道
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        JdbcUtil util = new JdbcUtil();
        Map map = new ConcurrentHashMap<>();
        for (int i = 1; i < 20; i++) {
            Connection con = util.getCon();
//            System.out.println("启动时创建Connection " + con);
            map.put(con, true); // true表示通道处于空闲状态
        }
        ServletContext application = sce.getServletContext();
        application.setAttribute("conPool", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (ConcurrentHashMap) application.getAttribute("conPool");
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Connection con = (Connection) it.next();
            if (con != null) {
                try {
                    con.close();
//                    System.out.println("Connection关闭");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
