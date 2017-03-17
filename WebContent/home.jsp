<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<%
String isActive = (String) session.getAttribute("loggedIn");
 %>
<% if(isActive != null && isActive.equalsIgnoreCase("true")){%>
<h1> Welcome to Home Page</h1>

<br><br>
<%
String msg = (String) request.getAttribute("newUsermsg");
if( msg != null && msg.trim().length() > 0) {
 %>
 <div style="text-align: center; color: red"> <%= msg %> </div> <br><br>
 <%} %>
<a href="changepassword.jsp"> Change Password </a><br>
<a href="LogoutServlet"> Logout </a>

<%}else{ %>

<jsp:forward page="login.jsp"/>
<%} %>
</body>
</html>