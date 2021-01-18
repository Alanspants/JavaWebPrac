package bjpowernode.controller;

import bjpowernode.dao.DeptDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeptDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String deptNo = null;
        DeptDao dao = new DeptDao();
        int flag = 0;
        PrintWriter out = null;

        // 1. 调用请求对象读取请求头参数得到部门编号
        deptNo = request.getParameter("deptNo");

        // 2. 调用dao对象推送delete命令到数据库服务器中执行并带回处理结果
        flag = dao.delete(deptNo);

        // 3. 调用响应对象，将处理结果写入到响应体中
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if (flag == 1) {
            out.print("<center><font style='color:red;font-size:35px'>部门删除成功</font></center>");
        } else {
            out.print("<center><font style='color:red;font-size:35px'>部门删除失败</font></center>");
        }
    }
}
