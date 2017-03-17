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
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	java.sql.Statement stmt = null ;
	ResultSet rs = null ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String oldpassword = request.getParameter("oldpass");
		oldpassword = Modulo26Crypto.encrypt(oldpassword, username.substring(0, 1));
		String newpass = request.getParameter("pass1");
		newpass = Modulo26Crypto.encrypt(newpass, username.substring(0, 1));
		String msg = "Old Password is wrong. Please try again";
		boolean reset = false;
		try{
			conn = JDBCUtil.getOracleConnection();
			String query = "select password from USERS_LOGIN where username = '" + username + "'";
			System.out.println("Select Query is : " + query);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()){
				String val = rs.getString("password");
				if(val != null && val.equals(oldpassword)){
					reset = true;
				}
			}
			
			if(reset){
				String updateQuery = "update USERS_LOGIN set password = '" + newpass + "' where username = '" + username +"'";
				stmt = conn.createStatement();
				int i = stmt.executeUpdate(updateQuery);
				System.out.println("Update Query is : " + updateQuery);
				if(i > 0){
					msg = "Password changed successfully";
				}else{
					reset = false;
					msg = "Failed to update password. Please try again later.";
				}
			}
			
			RequestDispatcher rd = null;
			request.setAttribute("newUsermsg", msg);
			System.out.println(msg);
			if(reset){
				rd = request.getRequestDispatcher("home.jsp");
			}else{
				rd = request.getRequestDispatcher("changepassword.jsp");
			}
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.cleanup(stmt, conn, rs);
		}
	}

}
