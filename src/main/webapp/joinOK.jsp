<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>joinOK.jsp</title>
</head>
<body>
	<%!
		String name, id, pw, ph1, ph2, ph3, gender;
	%>
	
	<%
		request.setCharacterEncoding("EUC-KR");
		name = request.getParameter("name");
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		ph1 = request.getParameter("ph1");
		ph2 = request.getParameter("ph2");
		ph3 = request.getParameter("ph2");
		gender = request.getParameter("gender");
	%>
	
	�̸� : <%= name %><br/>
	���̵� : <%= id %><br/>
	��й�ȣ : <%= pw %><br/>
	��ȭ��ȣ : <%= ph1 %>-<%= ph2 %>-<%= ph3 %><br/>
	���� : <%= gender %><br/>
</body>
</html>