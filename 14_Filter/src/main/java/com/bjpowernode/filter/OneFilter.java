package com.bjpowernode.filter;

import javax.servlet.*;
import java.io.IOException;

public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 1. 从拦截得到的请求对象读取请求头参数，得到用户年龄
        String age = request.getParameter("age");

        // 2. 根据用户年龄判断本次请求是否合法
        if (Integer.valueOf(age) < 70){
            // 合法请求
            chain.doFilter(request, response);
        } else {
            // 非法请求，由过滤器代替tomcat拒绝本次请求
            System.out.println("cannot pass");
            response.setContentType("test/html;charset=utf-8");
            response.getWriter().print("Cannot visit");
        }
    }
}
