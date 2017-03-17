package com.test.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.jdbc.JDBCUtil;
import com.test.security.Modulo26Crypto;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
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
		String password = request.getParameter("pass1");
		password = Modulo26Crypto.encrypt(password, username.substring(0, 1));
		String email = request.getParameter("email");
		String msg = "Sorry couldn't create the user, please try after some time";
		boolean regUser = true;
		try{
			conn = JDBCUtil.getOracleConnection();
			String query = "select * from USERS_LOGIN where username = '" + username + "'";
			System.out.println("Query is : " + query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()){
				regUser = false;
				msg = "Sorry username already exists, choose a different one";
			}
			
			if(regUser){
				regUser = false;
				String insertQuery = "insert into USERS_LOGIN values ('" + username + "','" + password + "','" + email +"')";
				stmt = conn.createStatement();
				int i = stmt.executeUpdate(insertQuery);
				System.out.println("Update Query is : " + insertQuery);
				if(i > 0){
					regUser = true;
					msg = "User created successfully, try logging in";
				}
			}
			
			RequestDispatcher rd = null;
			request.setAttribute("newUsermsg", msg);
			System.out.println(msg);
			if(regUser){
			rd = request.getRequestDispatcher("login.jsp");
			}else{
			rd = request.getRequestDispatcher("register.jsp");	
			}
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.cleanup(stmt, conn, rs);
		}
	}

}
