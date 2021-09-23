package com.javaGG.ex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOk
 */
@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private Connection connection;
	private Statement stmt;
	private ResultSet resultSet;
	private String name, id, pw, ph1, ph2, ph3, gender;
	
    public LoginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // TODO Auto-generated method stub
	      
	      request.setCharacterEncoding("EUC-KR");
	      
	      id = request.getParameter("id");
	      pw = request.getParameter("pw");
	      // '" + 변수 + "'
	      String query = "select * from members where id='" + id + "' and pw='" + pw + "' ";
	      
	      try {
	    	  Class.forName("oracle.jdbc.driver.OracleDriver");
	    	  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
	    	  stmt = connection.createStatement();
	    	  resultSet = stmt.executeQuery(query);
	    	  
	    	  while(resultSet.next()){ //데이터를 차례대로 불러옴(데이터가 있으면 참, 없으면(null) 거짓)
	              name =  resultSet.getString("name");
	              id = resultSet.getString("id");
	              pw =  resultSet.getString("pw");
	              ph1 =  resultSet.getString("ph1");
	              ph2 =  resultSet.getString("ph2");
	              ph3 =  resultSet.getString("ph3");
	              gender =  resultSet.getString("gender");
	          }
	    	  
	    	  HttpSession httpSession = request.getSession();//세션 객체 생성
	    	  httpSession.setAttribute("name",name);
	    	  httpSession.setAttribute("id",id);
	    	  httpSession.setAttribute("pw",pw);
	    	  httpSession.setAttribute("gender", gender);
	    	  
	    	  response.sendRedirect("loginResult.jsp");//받은 값 loginResult.jsp로 보내기
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }finally {
	          
	          try{
	             if(stmt != null) stmt.close();
	             if(connection != null) connection.close();
	          } catch(Exception e) {
	             e.printStackTrace();
	          }
	       }
	}

}
