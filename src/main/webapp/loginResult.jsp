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
	<%= name %>�� �α��ο� �����ϼ̽��ϴ�.<br>
	<%= name %>���� ID�� "<%= id %>" �Դϴ�.<br>
	<%= name %>���� ������ "<%= gender %>" �Դϴ�.<br>
	<a href ="modify.jsp">[ȸ����������]</a>	
</body>
</html>