<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="login.css"/>
<script src="login.js"></script>
</head>
<body>
  <form action="LoginServlet" onSubmit=" return validationForm()">
     <label for="email">Email:</label>
     <input id="email" type="email" name="email" placeholder="email"/>
     <label for="pass">Password:</label>
     <input id="pass" type="password" name="pass" placeholder="password"/>
     <input id="login" type="submit" value="Login"/>
     <div class="error_msg_con">
       <p id="error_msg" style="color: red; display: <%-- Only show if errorMessage is present --%>
      <%= request.getAttribute("errorMessage") != null ? "block" : "none" %>;">
        <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
      </p>
      </div>
     <p>Don't have an account? <a href="signup.jsp" style="text-decoration: none;">SignUp</a></p>
  </form>
</body>
</html>