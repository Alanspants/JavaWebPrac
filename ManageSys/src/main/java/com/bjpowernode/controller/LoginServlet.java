package com.bjpowernode.controller;

import com.bjpowernode.dao.DeptDao;
import com.bjpowernode.dao.EmpDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null, password = null, ck = null;
        int flag = 0;
        RequestDispatcher report = null;
        EmpDao dao = new EmpDao();
        Cookie cookieArray[] = null;

        // 1. 读取请求体参数，得到来访信息
        request.setCharacterEncoding("utf-8");
        username = request.getParameter("username");
        password = request.getParameter("password");
        ck = request.getParameter("ck");

        // 情况1：新用户首次访问
        if (username == null && password == null) {
            cookieArray = request.getCookies();
            if (cookieArray != null) {
                for (Cookie cookie : cookieArray) {
                    String keyName = cookie.getName();
                    if ("bjpowernode".equals(keyName)) {
                        request.getRequestDispatcher("/index.html").forward(request, response);
                        return;
                    }
                }
            }
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }

        // 情况2：用户主动提供来访信息
        if (!"".equals(username) && !"".equals(password)) {
            // 调用dao核实用户身份
            flag = dao.login(username, password);
            if (flag == 1) {
                // 询问用户是否需要十天之内免登录
                if (ck != null) {
                    Cookie cookie = new Cookie("bjpowernode", username);
                    cookie.setMaxAge(60 * 60 * 24 * 10);
                    response.addCookie(cookie);
                }
                request.getRequestDispatcher("/index.html").forward(request, response);
                return;
            }
        }

        // 情况3：用户主动选择十天免登录
        cookieArray = request.getCookies();
        if (cookieArray != null) {
            for (Cookie cookie : cookieArray) {
                String keyName = cookie.getName();
                if ("bjpowernode".equals(keyName)) {
                    request.getRequestDispatcher("/index.html").forward(request, response);
                    return;
                }
            }
        }

        // 情况4：来访的用户没有合法信息，拒绝进入
        request.getRequestDispatcher("/login_error.html").forward(request, response);

    }

}
