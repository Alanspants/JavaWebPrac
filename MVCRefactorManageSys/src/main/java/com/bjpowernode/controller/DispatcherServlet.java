package com.bjpowernode.controller;

import com.bjpowernode.service.BaseService;
import org.omg.IOP.ServiceContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = null;
        String serviceName = null;
        String classPath = null;
        Method method = null;
        BaseService service = null;
        String serviceMethod = null;
        ServletContext application = request.getServletContext();

        // 1. 调用请求对象读取请求行uri
        uri = request.getRequestURI(); // "/网站名/service别名.do"
        // 2. 获得本次被访问的service类的别名
        serviceName = uri.substring(uri.lastIndexOf("/") + 1);
        // 3. 从全局作用域对象中得到被访问的类
        classPath = application.getInitParameter(serviceName);
        System.out.println(classPath);
        // 4. 创建service类的实例对象
        try {

            service = getService(classPath, application);

            // 5. 初始化service对象
            initService(service, request);

            // 6. 根据请求调用service对象中的方法处理请求
            serviceMethod = request.getParameter("method");
            method = service.getClass().getDeclaredMethod(serviceMethod, null);
            String result = (String) method.invoke(service, null);
            request.getRequestDispatcher(result).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Service对象创建
    private BaseService getService(String classPath, ServletContext application) {
        Class classFile = null;

        // 1. 检测被访问的Service类的实例对象是否存在
        BaseService service = (BaseService) application.getAttribute(classPath);
        if (service != null) {
            return service;
        }
        // 2. 创建service对象
        try {
            classFile = Class.forName(classPath);
            service = (BaseService) classFile.newInstance();
            application.setAttribute(classPath, service);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return service;

    }

    // Service对象初始化--自动为service对象属性进行赋值
    private void initService(BaseService service, HttpServletRequest request) {

        Class classFile = null;
        Field fieldArray[] = null;

        // 1. 需要获得当前service对象隶属的类文件
        classFile = service.getClass();
        // 2. 需要获得当前service类文件声明的属性信息
        fieldArray = classFile.getDeclaredFields();
        // 3. 对service类对象的属性进行初始化操作---new dao对象
        for (Field field : fieldArray) {
            field.setAccessible(true);
            String daoTypeName = field.getType().getName(); // "com.bjpowernode.dao.DeptDao"
            Object daoInstance = request.getServletContext().getAttribute(daoTypeName);
            if (daoInstance != null) {
                try {
                    field.set(service, daoInstance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    classFile = Class.forName(daoTypeName);
                    daoInstance = classFile.newInstance();
                    field.set(service, daoInstance);
                    request.getServletContext().setAttribute(daoTypeName, daoInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        service.request = request;
    }
}
