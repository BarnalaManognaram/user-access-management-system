package com.usma.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        String username=request.getParameter("email");
	        String password=request.getParameter("pass");
        try {
        	String driver="org.postgresql.Driver";
	    	Class.forName(driver);
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAccessManagement",
		            "postgres", "Ram9059");
		 String sql = "SELECT * FROM users WHERE username=? AND password=?";
			   PreparedStatement st=connection.prepareStatement(sql);
		 st.setString(1, username);
			   st.setString(2, password);
			   ResultSet rs = st.executeQuery();
				   if(rs.next()) {
					   out.println("Authenticated"+rs.getString("username"));
				   }
				   else {
					   request.setAttribute("errorMessage", "Invalid username or password");
		                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		                dispatcher.forward(request, response);
				   }
				   
		        }
		        catch(Exception e) {
		        	out.println(e);
		        }
	    }
}
