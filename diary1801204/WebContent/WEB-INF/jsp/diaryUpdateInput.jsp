<%@ page import="diary.bean.DiaryBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌修正入力</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
</head>
<body>
<h3>日誌修正</h3>
<form action="diaryupdatecheck" method="post">
    <p>良い点</p>
    <textarea name="good-point" cols="10" rows="3"><%=diary_beans.getGood_point()%></textarea>
    <p>悪い点</p>
    <textarea name="bad-point" cols="10" rows="3"><%=diary_beans.getBad_point()%></textarea>
    <p>コメント</p>
    <textarea name="student-comment" cols="10" rows="3"><%=diary_beans.getStudent_comment()%></textarea>

    <input type="submit" value="修正">
</form>

<form action="select" method="get">
    <input type="submit" value="戻る">
</form>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
