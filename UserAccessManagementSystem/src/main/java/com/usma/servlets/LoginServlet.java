package com.usma.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
					   HttpSession session = request.getSession();
		                session.setAttribute("user_id", rs.getInt("id"));
		                session.setAttribute("role", rs.getString("role"));
					  if(rs.getString("role").equals("Employee")) {
						  String softwareSQL = "SELECT * FROM softwares";
						   PreparedStatement stm=connection.prepareStatement(softwareSQL);
						   ResultSet softwareRs = stm.executeQuery();
						   Map<Integer, String> softwareList = new HashMap<>();
						    while (softwareRs.next()) {
						        softwareList.put(softwareRs.getInt("id"), softwareRs.getString("name")); // Store id as key, name as value
						    }
						   request.setAttribute("softwareList", softwareList);
						   RequestDispatcher dispatcher = request.getRequestDispatcher("requestAccess.jsp");
						    dispatcher.forward(request, response);
					  }
					  if(rs.getString("role").equals("Admin")) {
						  RequestDispatcher dispatcher = request.getRequestDispatcher("createSoftware.jsp");
						    dispatcher.forward(request, response);
					  }
					  if(rs.getString("role").equals("Manager")) {
						  RequestDispatcher dispatcher = request.getRequestDispatcher("ApprovalServlet");
						    dispatcher.forward(request, response);
					  }
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
