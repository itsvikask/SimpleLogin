<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>

<h1 style="text-align: center"> Welcome to Password Reset Page</h1>
<br><br>

<div style="text-align: center">
Please enter your username. Password will be sent to registered Email Id. <br><br>

<form action="ForgotPasswordServlet" method="post">
Username : <input type="text" name="username" id="username"  maxlength="20" /><span id='useMsg'></span><br><br>
<input type="submit" id="submitB" value="Reset" />
</form>
<br><br>
<a href="login.jsp"> Back to Login Page </a>
</div>
<br><br>
<%
String msg = (String) request.getAttribute("newUsermsg");
if( msg != null && msg.trim().length() > 0) {
 %>
 <div style="text-align: center; color: red"> <%= msg %> </div> <br><br>
 <%} %>
 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" ></script>
<script type="text/javascript">
	
	$('#submitB').click(function(){
     	if($('#username').val() != '' && trim($('#username')).length > 0 ){
     		return true;
     	}
     	$('#useMsg').html('Username cannot be Blank').css('color', 'red');
     	return false;
     });

</script>

</body>
</html>