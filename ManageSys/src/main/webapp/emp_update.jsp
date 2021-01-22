<%@ page import="com.bjpowernode.entity.Emp" %>
<%@ page import="com.bjpowernode.entity.Dept" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Chz
  Date: 22/1/21
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%
        List<Emp> empList = (List<Emp>) request.getAttribute("empList");
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
    %>

    <script type="text/javascript">
        window.onload = function () {
            // 设置职员对应领导
            var empMgr = ${requestScope.empKey.mgr};
            var mgrDom = document.getElementById("mgr");
            for (i = 0; i < mgrDom.options.length; i++) {
                var optionDom = mgrDom[i];
                if(optionDom.value == empMgr){
                    optionDom.selected = true;
                    break;
                }
            }

            // 设置职员对应部门
            var emp_deptNo = ${requestScope.empKey.deptNo};
            var mgrDom = document.getElementById("deptNo");
            for (i = 0; i < mgrDom.options.length; i++) {
                var optionDom = mgrDom[i];
                if(optionDom.value == emp_deptNo){
                    optionDom.selected = true;
                    break;
                }
            }
        }
    </script>
</head>
<body>
<center>
    <form action="/myWeb/emp/add">
        <table border="2">
            <tr>
                <td>职员编号</td>
                <td><input type="text" name="empNo" value="${requestScope.empKey.empNo}" readonly></td>
            </tr>
            <tr>
                <td>职员姓名</td>
                <td><input type="text" name="ename" value="${requestScope.empKey.ename}"></td>
            </tr>
            <tr>
                <td>职员职位</td>
                <td><input type="text" name="job" value="${requestScope.empKey.job}"></td>
            </tr>
            <tr>
                <td>上司编号</td>
                <td><select name="mgr" id="mgr">
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
                <td><input type="text" name="sal" value="${requestScope.empKey.sal}"></td>
            </tr>
            <tr>
                <td>职员补助</td>
                <td><input type="text" name="comm" value="${requestScope.empKey.comm}"></td>
            </tr>
            <tr>
                <td>入职日期</td>
                <td><input type="text" name="hireDate" value="${requestScope.empKey.hireDate}"></td>
            </tr>
            <tr>
                <td>部门编号</td>
                <td><select name="deptNo" id="deptNo">
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
