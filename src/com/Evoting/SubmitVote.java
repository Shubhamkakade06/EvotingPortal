package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SubmitVote")
public class SubmitVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();

		
		PrintWriter out = response.getWriter(); 
		String partyname=request.getParameter("partyname");
		String emailid=(String) session.getAttribute("emailid");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from votecount");
		
		if(!rs.next())
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			
			
			
				Statement stmt1=con.createStatement();
				int i=stmt1.executeUpdate("insert into votecount (PartyName,TotalVotes) select Party_Name,0 from candidates");
				
			
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			
			System.out.println(emailid);
			PreparedStatement stmt2=con.prepareStatement("select * from voters where Email_Id=?");
			stmt2.setString(1, emailid);
			ResultSet rs2=stmt2.executeQuery();
			while(rs2.next())
			{
					
				
				if(rs2.getString(13)==null)
				{
					PreparedStatement stmt3=con.prepareStatement("update  voters set Voted=\"Yes\" Where Email_Id=?");
					stmt3.setString(1, emailid);
					stmt3.executeUpdate();
					
					PreparedStatement stmt=con.prepareStatement("update votecount SET TotalVotes=TotalVotes + 1 where PartyName=?");
					stmt.setString(1, partyname);
					stmt.executeUpdate();
					
		        	out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('SUCCESS !', 'You Successfully Voted to Candidate...Thank you For Your Valuable Vote', 'success');");
		            out.println("});");
		            out.println("</script>");
		            RequestDispatcher rd = request.getRequestDispatcher("Dashboard.html");
		            rd.include(request, response);
				}
				else
				{
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('ERROR !', 'You Already Voted...Thank You', 'error');");
		            out.println("});");
		            out.println("</script>");
		            RequestDispatcher rd = request.getRequestDispatcher("Dashboard.html");
		            rd.include(request, response);
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

	
}
