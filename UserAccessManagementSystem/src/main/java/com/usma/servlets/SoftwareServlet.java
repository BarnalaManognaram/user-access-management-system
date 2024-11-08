package com.usma.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminAccessServlet
 */
@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        
        String sName=request.getParameter("sName");
        String desc=request.getParameter("desc");
        String accessLevel=request.getParameter("access_level");
        try {
    	Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAccessManagement",
	            "postgres", "Ram9059");
		 String sql = "INSERT INTO softwares(name,description,accesslevels) VALUES(?,?,?)";
		   PreparedStatement st=connection.prepareStatement(sql);
		   st.setString(1,sName);
		   st.setString(2, desc);
		   st.setString(3,accessLevel);
	       st.executeUpdate();
	       out.println("Updated Successfully");
        }
        catch(Exception e) {
        	out.println(e);
        }
	}

}
