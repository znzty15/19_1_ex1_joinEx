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
	<h3>�α׾ƿ� �Ǿ����ϴ�.</h3>
	<a href="login.html">�α���</a>
</body>
</html>