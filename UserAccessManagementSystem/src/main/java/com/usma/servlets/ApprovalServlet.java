package com.usma.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ApprovalServlet
 */
@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        int requestId = Integer.parseInt(request.getParameter("requestId"));

        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAccessManagement",
                    "postgres", "Ram9059");

            String updateSql = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(updateSql);
            if ("approve".equals(action)) {
                stmt.setString(1, "Approved");
            } else if ("reject".equals(action)) {
                stmt.setString(1, "Rejected");
            }
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
            stmt.close();
            connection.close();
            response.sendRedirect("ApprovalServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error updating status: " + e.getMessage() + "</p>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String role=(String)session.getAttribute("role");
        if(role.equals("Manager")|| role.equals("Admin")) {
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAccessManagement",
                    "postgres", "Ram9059");

            String sql = "SELECT * FROM requests WHERE status = 'Pending'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
         
            out.println("<table border='1' cellspacing='0' cellpadding='5'>");
            out.println("<tr>");
            out.println("<th>Username</th>");
            out.println("<th>Software Name</th>");
            out.println("<th>Access Type</th>");
            out.println("<th>Reason</th>");
            out.println("<th>Status</th>");
            out.println("<th>Approve</th>");
            out.println("<th>Reject</th>");
            out.println("</tr>");

            while (rs.next()) {
                int requestId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int softwareId = rs.getInt("software_id");
                String username = getUsername(connection, userId);
                String softwareName = getSoftwareName(connection, softwareId);

                out.println("<tr>");
                out.println("<td>" + username + "</td>");
                out.println("<td>" + softwareName + "</td>");
                out.println("<td>" + rs.getString("access_type") + "</td>");
                out.println("<td>" + rs.getString("reason") + "</td>");
                out.println("<td>" + rs.getString("status") + "</td>");
                out.println("<td>");
                out.println("<form method='post' action='ApprovalServlet'>");
                out.println("<input type='hidden' name='requestId' value='" + requestId + "'/>");
                out.println("<input type='hidden' name='action' value='approve'/>");
                out.println("<input type='submit' value='Approve'/>");
                out.println("</form>");
                out.println("</td>");
                out.println("<td>");
                out.println("<form method='post' action='ApprovalServlet'>");
                out.println("<input type='hidden' name='requestId' value='" + requestId + "'/>");
                out.println("<input type='hidden' name='action' value='reject'/>");
                out.println("<input type='submit' value='Reject'/>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            rs.close();
            stm.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error retrieving data: " + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
        }
        else {
        	out.println("U "+role+"s dont have access to this");
        }
    }

    private String getUsername(Connection connection, int userId) throws SQLException {
        String sql = "SELECT username FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        String username = "N/A"; // Default if no username is found
        if (rs.next()) {
            username = rs.getString("username");
        }
        rs.close();
        stmt.close();
        return username;
    }

    private String getSoftwareName(Connection connection, int softwareId) throws SQLException {
        String sql = "SELECT name FROM softwares WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, softwareId);
        ResultSet rs = stmt.executeQuery();
        String softwareName = "N/A"; // Default if no software name is found
        if (rs.next()) {
            softwareName = rs.getString("name");
        }
        rs.close();
        stmt.close();
        return softwareName;
    }
}
