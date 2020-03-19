package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduleVotings
 */
@WebServlet("/ScheduleVotings")
public class ScheduleVotings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String votingdate=request.getParameter("votingdate");
		PrintWriter out=response.getWriter();
		try
		{
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse(votingdate);
			java.sql.Date sqldate=new java.sql.Date(date.getTime());
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement stmt=con.prepareStatement("insert into votingschedule values(?)");
			stmt.setDate(1, sqldate);
			stmt.executeUpdate();
			
			Statement stmt1=con.createStatement();
			int i=stmt.executeUpdate("update voters SET Voted=NULL");
			
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal('SUCCESS !', 'Voting Schedule Successfully', 'success');");
            out.println("});");
            out.println("</script>");
            RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.html");
            rd.include(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
			
		
		
	}

	
}
