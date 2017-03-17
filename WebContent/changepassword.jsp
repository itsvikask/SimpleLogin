<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1 style="text-align: center">Change Password</h1>
<% String user = (String) session.getAttribute("loggedUser"); %>
<div style="text-align: center">
<form action="ChangePasswordServlet" method="post">
<input type="hidden" name="username" id="username" value='<%= user %>' />
Old Password: <input type="password" name="oldpass" id="oldpass" /> </br></br>
New Password : <input type="password" name="pass1" id="pass1" /> </br></br>
Confirm Password : <input type="password" name="pass2" id="pass2" /> <span id='message'></span> </br></br>
 <input type="submit" value="Submit" id="submitB" />
<input type="reset" value="Clear" /> 
</form>

<br><br>
<a href="home.jsp"> Back to Home Page </a>
</div>
<br><br>

</div>

<%
String msg = (String) request.getAttribute("newUsermsg");
if( msg != null && msg.trim().length() > 0) {
 %>
 </br><div style="text-align: center; color: red"> <%= msg %> </div> </br>
 <%} %>
 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js" ></script>
<script type="text/javascript">

	$('#pass1, #pass2').on('keyup', function () {
    if ($('#pass1').val() != '' && $('#pass1').length > 0 &&  $('#pass1').val() == $('#pass2').val()) {
        $('#message').html('Matching').css('color', 'green');
        document.getElementById('submitB').disabled = false;
    } else { 
        $('#message').html('Not Matching').css('color', 'red');
        document.getElementById('submitB').disabled = true;
     }
     
	});

</script>	

</body>
</html>