<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<title>Login</title>
<script type="text/javascript" src="js/Validation.js"></script>
</head>
<body>
<center>
<h1>Hello new user</h1>
<form action="takedata" modelAttribute="User"  method="POST" onSubmit="return doValidate()">
FirstName:<input type="text" name="firstName"><br>
LastName:<input type="text" name="lastName"><br>
UserName:<input type="text" name="userName"><br>
Password:<input type="password" id="password" name="password"><br>
Email:<input type="text" id="email" name="email"><br>

Gender:
 <input type="radio" name="gender" value="male" checked> Male&nbsp;
  <input type="radio" name="gender" value="female"> Female &nbsp;
  <input type="radio" name="gender" value="other"> Other<br>
 
  
   <input type="radio" name="policy" value="agree" checked> yes i agree&nbsp;
  <input type="radio" name="policy" value="disagree">sorry I do not agree with the policies &nbsp;<br>
<input type="submit"></a>
</form>

</center>
</body>
</html>