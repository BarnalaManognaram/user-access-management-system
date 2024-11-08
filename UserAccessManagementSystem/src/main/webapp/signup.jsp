<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="app.css"/>
<script src="signup.js"></script>
</head>
<body>
<div class="formContainer">
<form action="SignUpServlet" onSubmit=" return validationForm()" method="post">
<h2>Sign Up</h2>
  <label for="email">Email:</label>
     <input id="email" class="text" type="email" name="email" placeholder="Email"/>
     <label for="pass">Password:</label>
     <input id="pass" class="text" type="password" name="pass" placeholder="Password"/>
      <label for="conPass">Confirm Password:</label>
     <input id="conPass" class="text" type="password" name="conPass" placeholder="Confirm Password"/>
     <input id="signUp" class="button" type="submit" value="SignUp"/>
      <div class="error_msg_con">
      <p id="error_msg" style="color: red; display: <%-- Only show if errorMessage is present --%>
      <%= request.getAttribute("errorSignupMessage") != null ? "block" : "none" %>;">
        <%= request.getAttribute("errorSignupMessage") != null ? request.getAttribute("errorSignupMessage") : "" %>
      </p>
      </div>
     <p>Already have an account? <a href="login.jsp" style="text-decoration: none;">Log In</a></p>
</form>
</div>
</body>
</html>