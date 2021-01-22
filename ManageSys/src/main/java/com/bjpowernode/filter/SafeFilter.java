package com.bjpowernode.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 对于浏览器请求的与登陆验证相关资源文件，无条件放行
 * <p>
 * 登陆相关资源文件：
 * 1. LoginServlet
 * 2. Login.html (img, css, js)
 * 3. 文件名都要包含login
 */

public class SafeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String uri = null;
        HttpSession session = null;

//        // 2. 验证本次请求合法性
//        if(session == null){
//            request.getRequestDispatcher("/login_error.html").forward(request, response);
//        } else {
//            chain.doFilter(request, response);
//        }

        // 1.通过拦截的请求对象读取请求包中请求行中URI（/网站名/资源文件名）
        uri = ((HttpServletRequest) request).getRequestURI();

        // 2. 如果本次访问资源文件与登陆验证相关资源文件。无条件放行
        if (uri.indexOf("login") != -1 || uri.equals("/myWeb/")) {
            chain.doFilter(request, response);
            return;
        }

        // 3. 对于其他资源文件的访问，需要用户提交令牌（session）
        session = ((HttpServletRequest) request).getSession(false);
        if(session == null){
            request.getRequestDispatcher("/login_error.html").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
