<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
  <%
    HttpSession sessions = request.getSession(false);
    if (sessions == null){ 
        response.sendRedirect("login.jsp");  // Redirect to login or error page if not an employee
        return;
    }
    else if(!"Employee".equals(session.getAttribute("role"))){
    	out.println("Access request is only for employees not for Admins and Managers");
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
<form action="RequestAccessServlet">
<h2>Request Software Access</h2>
<label for="softwareDropdown">Select Software:</label>
<select id="softwareDropdown" class="text" name="softwareId">
    <%
        // Assuming a Map<Integer, String> named softwareList is set as an attribute by the servlet
        Map<Integer, String> softwareList = (Map<Integer, String>) request.getAttribute("softwareList");
        if (softwareList != null) {
            for (Map.Entry<Integer, String> entry : softwareList.entrySet()) {
                int id = entry.getKey();
                String name = entry.getValue();
                out.println("<option value='" + id + "'>" + name + "</option>");
            }
        }
    %>
</select>
<label for="accessDropdown">Access Type:</label>
<select id="accessDropdown" class="text" name="accessDropdown">
    <option value="Read">Read</option>
    <option value="Write">Write</option>
    <option value="Admin">Admin</option>
</select>
<label for="reason">Reason:</label>
     <input id="reason" class="text"  type="text" name="reason" placeholder="Reason for access" required/> 
     <input id="submitBtn" class="button" type="submit" value="Submit"/>
</form>
</body>
</html>