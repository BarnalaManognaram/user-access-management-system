package com.usma.servlets;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Set the response content type
	        response.setContentType("text/html");

	        // Get the output stream to write the response
	        PrintWriter out = response.getWriter();
	        
	        String username=request.getParameter("email");
	        String password=request.getParameter("pass");
	        try {
	    	Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAccessManagement",
		            "postgres", "Ram9059");
			 String sql = "INSERT INTO users(username,password,role) VALUES(?,?,?)";
			   PreparedStatement st=connection.prepareStatement(sql);
			   st.setString(1,username);
			   st.setString(2, password);
			   st.setString(3,"Employee");
		       st.executeUpdate();
		       out.println("Updated Successfully");
		       response.sendRedirect("login.jsp");
	        }
	        catch(SQLException e) {
	        	if("23505".equals(e.getSQLState())){
	        		 request.setAttribute("errorSignupMessage", "User Already Exists Please Login");
		                RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		                dispatcher.forward(request, response);
	        	}
	        }
	        catch(Exception e) {
	        	out.println(e);
	        }
	    }
}
