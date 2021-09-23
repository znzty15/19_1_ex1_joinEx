package com.javaGG.ex;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOk
 */

@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   private Connection connection;
   private Statement stmt;

   private String name,id,pw,pw2,ph1,ph2,ph3,gender;

   HttpSession httpsession;

   public ModifyOk() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      //response.getWriter().append("Served at: ").append(request.getContextPath());
      actionDo(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      //doGet(request, response);
      actionDo(request, response);
   }

   private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub

      //�ѱ�ó��
      request.setCharacterEncoding("EUC-KR");
      httpsession = request.getSession(); // �ټ������� ���簪�� �ҷ����� ������ �ʿ��ϴ�!
      //�Ķ���;�
      name = request.getParameter("name");
      //id = request.getParameter("id");
      id = (String)httpsession.getAttribute("id");
      pw = request.getParameter("pw");
      ph1 = request.getParameter("ph1");
      ph2 = request.getParameter("ph2");
      ph3 = request.getParameter("ph3");
      gender = request.getParameter("gender");
      
      if(pwCheck()) {
	      String query =    "update members set name='" + name + "', ph1='" + ph1 + "', ph2='" + ph2 + "', ph3='" + ph3 + "', gender='" + gender + "' where id='" + id + "' ";
	
	      //try catch ��
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
	         stmt = connection.createStatement();
	
	         int i  = stmt.executeUpdate(query); // query �����ϸ� 1, �����ϸ� 0
	
	         if(i==1) {
	            System.out.println("�������� ����"); // �ܼ�â�� ����ֱ�
	            httpsession.setAttribute("name", name); //������ �̸��� ���ǿ� overwrite
	            response.sendRedirect("modifyResult.jsp"); // �������� ���� �� modifyResult.jsp�� �̵�
	         } else {
	            System.out.println("�������� ����"); // �ܼ�â�� ����ֱ�
	            response.sendRedirect("modify.jsp"); // ���������� �����ϸ� modify.jsp�� �̵��Ͽ� �ٽ� ���� �Է�
	         }
	
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(stmt != null) stmt.close();
	            if(connection != null) connection.close();
	         }catch (Exception e) {
	            e.printStackTrace();
	         }
	
	      }
      }else {
    	  System.out.println("��й�ȣ�� Ʋ���ϴ�.");
    	  response.sendRedirect("modify.jsp");
      }
   }
   private boolean pwCheck() {
	   boolean rs = false;
	   //���ǿ� �ִ� ���� �Է¹��� ���� ������ ��(��й�ȣ �´��� Ȯ��)
	   String spw = (String)httpsession.getAttribute("pw");
	   
	   if(spw.equals(pw))
		   rs = true;
	   else
		   rs = false;
	   return rs;
   }

}