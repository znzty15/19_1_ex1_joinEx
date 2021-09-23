<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>loginResult.jsp</title>
</head>
<body>
	<%
		String name = (String)session.getAttribute("name");
		String id = (String)session.getAttribute("id");
		String pw = (String)session.getAttribute("pw");	
		String gender = (String)session.getAttribute("gender");	
	%>
	<%= name %>님 로그인에 성공하셨습니다.<br>
	<%= name %>님의 ID는 "<%= id %>" 입니다.<br>
	<%= name %>님의 성별은 "<%= gender %>" 입니다.<br>
	<a href ="modify.jsp">[회원정보수정]</a>	
</body>
</html>