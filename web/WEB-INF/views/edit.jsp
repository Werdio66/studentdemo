<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑界面</title>
</head>
<body>
    <form action="/student?cmd=save" method="post">
        <input hidden type="text" name="id" value="${student.id}">
        姓名：      <input type="text" name="name" value="${student.name}"><br>
        年龄：      <input type="text" name="age" value="${student.age}"><br>
        数学成绩：  <input type="text" name="math" value="${student.math}"><br>
        计算机成绩：<input type="text" name="computer" value="${student.computer}"><br>
        英语成绩：  <input type="text" name="english" value="${student.english}"><br>
        <input type="submit" value="保存">
    </form>
</body>
</html>
