<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="signup.css"/>
<script src="signup.js"></script>
</head>
<body>
<div class="formContainer">
<form action="SignUpServlet" onSubmit=" return validationForm()" method="post">
  <label for="email">Email:</label>
     <input id="email" type="email" name="email" placeholder="Email"/>
     <label for="pass">Password:</label>
     <input id="pass" type="password" name="pass" placeholder="Password"/>
      <label for="conPass">Confirm Password:</label>
     <input id="conPass" type="password" name="conPass" placeholder="Confirm Password"/>
     <input id="signUp" type="submit" value="SignUp"/>
      <div class="error_msg_con">
      <p id="error_msg" style="color: red; display:none;">* Email field is required</p>
      </div>
     <p>Already have an account? <a href="login.jsp" style="text-decoration: none;">Log In</a></p>
</form>
</div>
</body>
</html>