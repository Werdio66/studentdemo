<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息列表</title>
</head>
<body>
    <table width="570" border="1">
        <tr>
            <a href="/student?cmd=edit">添加学生</a>
        </tr>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
            <td>数学成绩</td>
            <td>计算机成绩</td>
            <td>英语成绩</td>
            <td>平均成绩</td>
        </tr>
        <c:forEach items="${students}" var="stu">
            <tr>
                <td>${stu.name}</td>
                <td>${stu.age}</td>
                <td>${stu.math}</td>
                <td>${stu.computer}</td>
                <td>${stu.english}</td>
                <td>${stu.average}</td>
                <td>
                    <a href="/student?cmd=delete&id=${stu.id}">删除</a>
                </td>
                <td>
                    <a href="/student?cmd=edit&id=${stu.id}">编辑</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
