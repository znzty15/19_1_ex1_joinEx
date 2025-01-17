package com.javaGG.ex;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class joinOk
 */
@WebServlet("/JoinOk")
public class JoinOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private Connection connection;
	private Statement stmt;
	private String name, id, pw, ph1, ph2, ph3, gender;
	
    public JoinOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet!");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
		System.out.println("doPost!");
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // TODO Auto-generated method stub
	      
	      request.setCharacterEncoding("EUC-KR");
	      
	      name = request.getParameter("name");
	      id = request.getParameter("id");
	      pw = request.getParameter("pw");
	      ph1 = request.getParameter("ph1");
	      ph2 = request.getParameter("ph2");
	      ph3 = request.getParameter("ph3");
	      gender = request.getParameter("gender");
	      // '" + 변수 + "'
	      String query = "insert into members values('" + name + "','" + id + "','" + pw + "','" + ph1 + "','" + ph2 + "','" + ph3 + "','" + gender + "')";
	      
	      try {
	    	  Class.forName("oracle.jdbc.driver.OracleDriver");
	    	  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
	    	  stmt = connection.createStatement();
	    	  int i = stmt.executeUpdate(query);// 성공시 1, 실패시 0
	    	  if(i == 1) {
	    		  System.out.println("DB 저장 성공");
	    		  response.sendRedirect("joinResult.jsp");//DB 저장 성공시 joinResult.jsp로 이동
	    	  }else {
	    		  System.out.println("DB 저장 실패");
	    		  response.sendRedirect("join.html");//DB 저장 실패시 join.html로 이동	    		  
	    	  }
	    	  
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
