<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>logout.jsp</title>
</head>
<body>
	<%
		session.invalidate();
	%>
	<h3>로그아웃 됬습니다.</h3><br>
	<a href="login.html">로그인</a>
</body>
</html>