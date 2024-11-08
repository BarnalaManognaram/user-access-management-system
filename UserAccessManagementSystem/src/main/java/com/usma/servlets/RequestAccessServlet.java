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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeAccessServlet
 */
@WebServlet("/RequestAccessServlet")
public class RequestAccessServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int userId=(int)session.getAttribute("user_id");
        String softwareIdParam=request.getParameter("softwareId");
        String accessType=request.getParameter("accessDropdown");
        String reason=request.getParameter("reason");
        try {
        int softwareId=Integer.parseInt(softwareIdParam);
    	Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAccessManagement",
	            "postgres", "Ram9059");
		String sql = "INSERT INTO requests(user_id,software_id,access_type,reason,status) VALUES(?,?,?,?,?)";
		   PreparedStatement st=connection.prepareStatement(sql);
		   st.setInt(1,userId);
		   st.setInt(2, softwareId);
		   st.setString(3,accessType);
		   st.setString(4,reason);
		   st.setString(5, "Pending");
	       st.executeUpdate();
	       out.println("Updated Successfully");
        }
        catch(Exception e) {
        	out.println(e);
        }
	}

}
