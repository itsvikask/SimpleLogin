<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<%
String isActive = (String) session.getAttribute("loggedIn");
 %>
<% if(isActive != null && isActive.equalsIgnoreCase("true")){%>
<jsp:forward page="home.jsp"/>
<%}else{ %>

<h1 style="text-align: center">Welcome to Login Page</h1>
<br><br>

<%
String msg = (String) request.getAttribute("newUsermsg");
if( msg != null && msg.trim().length() > 0) {
 %>
 <div style="text-align: center; color: red"> <%= msg %> </div> <br><br>
 <%} %>
 
<div style="text-align: center">

<form action="loginPage" method="post">
User name : <input type="text" name="username" id="username" /> <span id='useMsg'></span> <br><br>
Password : <input type="password" name="pass" id="pass" /> <span id='passMsg'></span> <br><br>

<%
msg = (String) request.getAttribute("Errormsg");
if( msg != null && msg.trim().length() > 0) {
 %>
 <div style="color: red"> <%= msg %> </div> <br><br>
 <%} %>
 <a href="register.jsp"> New User ? </a> &nbsp &nbsp
 <a href="forgot.jsp"> Forgot Password ? </a><br><br>
<input type="submit" value="Submit" id="submitB" />
<input type="reset" value="Clear" /> 
</form>
</div>
<%} %>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" ></script>
<script type="text/javascript">
	
	$('#submitB').click(function(){
		if($('#username').val() != '' && trim($('#username')).length > 0){
		
     	if($('#pass').val() != '' && trim($('#pass')).length > 0 ){
     		return true;
     	}
     	$('#passMsg').html('Please Enter Password').css('color', 'red');
     	return false;
     	}else{
     	$('#useMsg').html('Username cannot be Blank').css('color', 'red');
     	return false;
     	}
     });
	
</script>
</body>
</html>