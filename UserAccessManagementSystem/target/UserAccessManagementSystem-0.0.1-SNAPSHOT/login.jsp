<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="login.css"/>
</head>
<body>
  <form action="LoginServlet" method="post">
     <label for="email">Email:</label>
     <input id="email" type="email" name="email" placeholder="email" required/>
     <label for="pass">Password:</label>
     <input id="pass" type="password" name="pass" placeholder="password" required/>
     <input id="login" type="submit" value="Login"/>
  </form>
</body>
</html>