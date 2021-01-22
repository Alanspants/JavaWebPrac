<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.entity.Emp" %>
<%@ page import="com.bjpowernode.entity.Dept" %><%--
  Created by IntelliJ IDEA.
  User: Chz
  Date: 21/1/21
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <%
        List<Emp> empList = (List<Emp>) request.getAttribute("empList");
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
    %>
    <font style="color:#ff0000;font-size: 35px">员工注册页</font>
    <form action="/myWeb/emp/add">
        <table border="2">
            <tr>
                <td>职员姓名</td>
                <td><input type="text" name="ename"></td>
            </tr>
            <tr>
                <td>职员职位</td>
                <td><input type="text" name="job"></td>
            </tr>
            <tr>
                <td>上司编号</td>
                <td><select name="mgr">
                    <%
                        for (Emp emp : empList) {
                    %>
                    <option value="<%=emp.getEmpNo()%>"><%=emp.getEname()%>
                    </option>
                    <%
                        }
                    %>
                </select></td>
            </tr>
            <tr>
                <td>职员工资</td>
                <td><input type="text" name="sal"></td>
            </tr>
            <tr>
                <td>职员补助</td>
                <td><input type="text" name="comm"></td>
            </tr>
            <tr>
                <td>入职日期</td>
                <td><input type="text" name="hireDate"></td>
            </tr>
            <tr>
                <td>部门编号</td>
                <td><select name="deptNo">
                    <%
                        for (Dept dept : deptList) {
                    %>
                    <option value="<%=dept.getDeptNo()%>"><%=dept.getDname()%>
                    </option>
                    <%
                        }
                    %>
                </select></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册职员"></td>
                <td><input type="reset" value="重置"></td>
            </tr>
        </table>
    </form>
</center>

</body>
</html>
