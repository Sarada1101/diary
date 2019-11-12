<%@ page import="diary.bean.LoginInfoBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String student_name = ((LoginInfoBeans) session.getAttribute("login-info")).getStudent_name();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>トップ</title>
</head>
<body>
<header>
    <div class="image position-absolute"></div>
    <nav class="navbar navbar-expand-lg navbar-dark scrolling-navbar z-depth-1">
        <a class="navbar-brand" href="#"><strong class="text-white">DiaryManagementSystem</strong></a>
        <span class="text-white"><%=student_name%>さん</span>
    </nav>
</header>
</body>
</html>
