<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
	<%!
		Connection connection;
	   	Statement stmt;
	   	ResultSet resultset;
		
	   	String driver = "oracle.jdbc.driver.OracleDriver";  //driver �ּ�
	    String url = "jdbc:oracle:thin:@localhost:1521:xe"; //url �ּ�
	    String uid = "scott";                      			//user id
	    String upw = "tiger";                       		//user pw
	    
	   	String name, id, pw, ph1, ph2, ph3, gender;
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>modify.jsp</title>
</head>
<body>
   <%
	   id = (String) session.getAttribute("id");	
	   String query = "select * from members where id='" + id + "'";
	
	   Class.forName(driver);
       connection = DriverManager.getConnection(url, uid, upw);	//�����ϱ�
       stmt = connection.createStatement();           	 	 	//������ ��������
       resultset = stmt.executeQuery(query);            		//���� ��� ��
	
	   while (resultset.next()) { //������ �ҷ����� ���پ� ���ʴ�� null���� ���� �� ����
	      name = resultset.getString("name");
	      id = resultset.getString("id");
	      pw = resultset.getString("pw");
	      ph1 = resultset.getString("ph1");
	      ph2 = resultset.getString("ph2");
	      ph3 = resultset.getString("ph3");
	      gender = resultset.getString("gender");
  	 }
   %>

   <form action="ModifyOk" method="post">
      �̸� : <input type="text" name="name" size="5" value="<%=name%>"><br>
      ���̵� : <%=id%><br>
      ��й�ȣ : <input type="password" name="pw"><br>
      ��ȭ��ȣ : <select name="ph1">
         <option value="010">010</option>
         <option value="011">011</option>
         <option value="016">016</option>
         <option value="017">017</option>
         <option value="018">018</option>
      </select> -<input type="text" name="ph2" size="5" value=<%=ph2%>>
      -<input type="text" name="ph3" size="5" value=<%=ph3%>><br>

      <!-- �������� �޾Ƽ� if���� �̿��Ͽ� Ȯ�� -->
      <%
      	if (gender.equals("man")) {
      %>
      	���� : <input type="radio" name="gender" value="man" checked="checked">��
     	 	  <input type="radio" name="gender" value="woman">��
      <%
     	 } else {
      %>
     	 ���� : <input type="radio" name="gender" value="man">��
     	 	   <input type="radio" name="gender" value="woman" checked="checked">��
   	 <%
     	 }
      %>
      <!-- ���� ������ if�� -->
      <br>
      <br> <input type="submit" value="��������"> <input
         type="reset" value="���">
   </form>

</body>
</html>