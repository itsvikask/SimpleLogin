package com.test.servlet;

import com.test.security.*;
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.test.jdbc.JDBCUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	java.sql.Statement stmt = null ;
	ResultSet rs = null ;
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		password = Modulo26Crypto.encrypt(password, username.substring(0, 1));
		String msg = "Username/Password Wrong";
		boolean validUser = false;
		try{
		if(username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0){
			
				conn = JDBCUtil.getOracleConnection();
				String query = "select * from USERS_LOGIN where username = '" + username + "' and password = '" + password + "'";
				System.out.println("Query is : " + query);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				if(rs.next()){
					validUser = true;
				}
		}
		if(validUser){
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", "true");
			session.setAttribute("loggedUser", username);
			response.sendRedirect("home.jsp");
		}else{
			request.setAttribute("Errormsg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.cleanup(stmt, conn, rs);
		}
}
}
