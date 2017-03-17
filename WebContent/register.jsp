<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>

<h1 style="text-align: center"> Welcome to Registration Page </h1>

<div style="text-align: center">
<form id="reg" action="RegisterServlet" method="post">
Username : <input type="text" name="username" id="username"  maxlength="20" /><span id='useMsg'></span></br></br>
Password : <input type="password" name="pass1" id="pass1"  maxlength="20" /> </br></br>
Confirm Password : <input type="password" name="pass2" id="pass2"  maxlength="20" /> <span id='message'></span> </br></br>
Email : <input type="text" name="email" id="email"  maxlength="40" /><span id='emailMsg'></span></br></br>
<input type="submit" id="submitB" value="Register" disabled/>
</form>
<br><br>
<a href="login.jsp"> Back to Login Page </a>
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
	
	$('#submitB').click(function(){
		var validEmail = IsEmail($('#email').val());
		if(validEmail){
     	if($('#username').val() != '' && trim($('#username')).length > 0 ){
     		return true;
     	}
     	$('#useMsg').html('Username cannot be Blank').css('color', 'red');
     	return false;
     	}else{
     	$('#emailMsg').html('Email Id not valid').css('color', 'red');
     	return false;
     	}
     });
	
	function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}

</script>
</body>
</html>