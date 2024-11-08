<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%
    HttpSession sessions = request.getSession(false);
    if (sessions == null){ 
        response.sendRedirect("login.jsp");  // Redirect to login or error page if not an employee
        return;
    }
    else if(!"Admin".equals(session.getAttribute("role"))){
    	out.println("U dont have access to create softwares");
    			return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="app.css"/>
</head>
<body>
   <form action="SoftwareServlet" method="post">
   <h2>Create Software</h2>
     <label for="sName">Name:</label>
     <input id="sName" class="text" type="text" name="sName" placeholder="Name" required/>
     <label for="desc">Description:</label>
     <input id="desc" class="text"  type="text" name="desc" placeholder="Description" required/> 
     <label for="access_level">Access Levels:</label>
     <input id="access_level" class="text"  type="text" name="access_level" placeholder="Read" required/> 
     <input id="submitBtn" class="button" type="submit" value="Submit"/>
     <div class="error_msg_con">
      <p id="error_msg" style="color: red; display: <%-- Only show if errorMessage is present --%>
      <%= request.getAttribute("errorSignupMessage") != null ? "block" : "none" %>;">
        <%= request.getAttribute("errorSignupMessage") != null ? request.getAttribute("errorSignupMessage") : "" %>
      </p>
      </div>
   </form>
</body>
</html>