package com.test.servlet;

import com.test.mail.*;
import com.test.security.Modulo26Crypto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.jdbc.JDBCUtil;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
public class ForgotPasswordServlet extends HttpServlet {
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
		String msg = "Email has been sent to your registered email id";
		boolean userExists = false;
		boolean success = false;
		try{
			conn = JDBCUtil.getOracleConnection();
			String query = "select * from USERS_LOGIN where username = '" + username + "'";
			System.out.println("Query is : " + query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()){
				userExists = true;
				String email = rs.getString("email");
				if(email != null && email.trim().length() > 0 ){
					String password = rs.getString("password");
					password = Modulo26Crypto.decrypt(password, username.substring(0, 1));
					String sub = "Old Password for "+username ;
					String data = "<font color = 'red' size ='6'> Password for "+username+" is "+password+"</font>";
					success = MailService.sendMail(email, sub, data);
					if(success){
						System.out.println("***Mail Sent to "+ email  +"******");
					}else{
						msg = "Couldn't send email, please try again later";
						System.out.println("***Failed to send email to "+ email  +"******");
					}
				}else{
					success = false;
					msg = "Registered Email Id is not valid. Please contact Administrator";
				}
			}else{
				userExists = false;
				msg = "Sorry username not present";
			}
			
			RequestDispatcher rd = null;
			request.setAttribute("newUsermsg", msg);
			System.out.println(msg);
			if(success && userExists){
			rd = request.getRequestDispatcher("login.jsp");
			}else{
			rd = request.getRequestDispatcher("forgot.jsp");	
			}
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.cleanup(stmt, conn, rs);
		}
	}

}
