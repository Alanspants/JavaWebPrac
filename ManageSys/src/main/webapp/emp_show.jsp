<%@ page import="com.bjpowernode.entity.Emp" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Chz
  Date: 21/1/21
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.contextPath}/">
    <script type='text/javascript' src='/myWeb/common.js'></script>
</head>
<body>
<center>
    <table border="2">
        <tr>
            <td><input type="checkbox" onclick="func1()"/>全选/全不选</td>
            <td>职员编号</td>
            <td>职员姓名</td>
            <td>职员职位</td>
            <td>职员工资</td>
            <td>入职日期</td>
            <td>操作</td>
        </tr>

        <%
            List<Emp> empList = (List<Emp>) request.getAttribute("key");
            for (Emp emp : empList) {
        %>

        <tr>
            <td><input type="checkbox"/></td>
            <td><%=emp.getEmpNo()%></td>
            <td><%=emp.getEname()%></td>
            <td><%=emp.getJob()%></td>
            <td><%=emp.getSal()%></td>
            <td><%=emp.getHireDate()%></td>
            <td><a href="emp/findById?empNo=<%=emp.getEmpNo()%>">详细信息</a></td>
        </tr>

        <%
            }
        %>
    </table>
</center>
</body>
</html>
