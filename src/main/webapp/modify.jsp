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
		
	   	String driver = "oracle.jdbc.driver.OracleDriver";  //driver 주소
	    String url = "jdbc:oracle:thin:@localhost:1521:xe"; //url 주소
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
       connection = DriverManager.getConnection(url, uid, upw);	//연결하기
       stmt = connection.createStatement();           	 	 	//데이터 가져오기
       resultset = stmt.executeQuery(query);            		//실행 결과 값
	
	   while (resultset.next()) { //데이터 불러오기 한줄씩 차례대로 null값이 나올 때 까지
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
      이름 : <input type="text" name="name" size="5" value="<%=name%>"><br>
      아이디 : <%=id%><br>
      비밀번호 : <input type="password" name="pw"><br>
      전화번호 : <select name="ph1">
         <option value="010">010</option>
         <option value="011">011</option>
         <option value="016">016</option>
         <option value="017">017</option>
         <option value="018">018</option>
      </select> -<input type="text" name="ph2" size="5" value=<%=ph2%>>
      -<input type="text" name="ph3" size="5" value=<%=ph3%>><br>

      <!-- 젠더값을 받아서 if문을 이용하여 확인 -->
      <%
      	if (gender.equals("man")) {
      %>
      	성별 : <input type="radio" name="gender" value="man" checked="checked">남
     	 	  <input type="radio" name="gender" value="woman">여
      <%
     	 } else {
      %>
     	 성별 : <input type="radio" name="gender" value="man">남
     	 	   <input type="radio" name="gender" value="woman" checked="checked">여
   	 <%
     	 }
      %>
      <!-- 여기 까지가 if문 -->
      <br>
      <br> <input type="submit" value="정보수정"> <input
         type="reset" value="취소">
   </form>

</body>
</html>