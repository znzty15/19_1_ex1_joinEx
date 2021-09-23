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

	private String name,id,pw,ph1,ph2,ph3,gender;

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

		//한글처리
		request.setCharacterEncoding("EUC-KR");
		// 겟세션으로 현재값을 불러오는 과정 필요
		httpsession = request.getSession();
		//파라미터
		name = request.getParameter("name");
		//id = request.getParameter("id");
		id = (String)httpsession.getAttribute("id");
		pw = request.getParameter("pw");
		ph1 = request.getParameter("ph1");
		ph2 = request.getParameter("ph2");
		ph3 = request.getParameter("ph3");
		gender = request.getParameter("gender");
		String query = "update members set name='" + name + "', ph1 = '" + ph1 + "', p2 = '" + ph2 + "', ph3 = '" + ph3 + "', gender='" + gender + "' where id='" + id + "'";
		//try catch 문
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			stmt = connection.createStatement();

			int i  = stmt.executeUpdate(query);  // query가 성공하면 1, 실패하면 0

			if(i==1) {
				System.out.println("정보수정 성공"); // 콘솔창에 찍어주기
				httpsession.setAttribute("name", name);
				response.sendRedirect("modifyResult.jsp"); // 정보수정 성공하면 modifyResult.jsp 페이지로 이동
			} else {
				System.out.println("정보수정 실패"); // 콘솔창에 찍어주기
				response.sendRedirect("modify.jsp"); // 정보수정 실패하면 modify.jsp로 이동
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
	}

}