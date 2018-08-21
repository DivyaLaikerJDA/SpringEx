<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>login</title>
</head>
<body>
<a href="index.jsp"><button type="submit">back</button></a>
<center>
<h1>welcome back user</h1>

<form action="loginuser" method="POST" modelAttribute="User" >
UserName:<input type="text" name="userName"><br>
<%
     String error = "";
     if(request.getAttribute("usernameErrorMessage") != null){
          error = request.getAttribute("usernameErrorMessage").toString();
      }	
	%>
	<p><%=error %>

Password:<input type="password" name="password"><br>

<%
     String passwordError = "";
     if(request.getAttribute("passwordError") != null){
   	  passwordError = request.getAttribute("passwordError").toString();
      }	
   
	%>
	
	<p><%=passwordError %>
<input type="submit">
<a href="forgotpasswordform">ForgotPassword</a>
</form>
</center>
</body>
</html>