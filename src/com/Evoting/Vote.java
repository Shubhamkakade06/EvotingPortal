package com.Evoting;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 PrintWriter out=response.getWriter();
		 HttpSession session=request.getSession();
		 String emailid=(String) session.getAttribute("emailid");
		System.out.println(emailid);
		
		
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
		Date currdate=new Date();
		String currentdate=dateformat.format(currdate);
		System.out.print(currentdate);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from votingschedule");
			
			while(rs.last())
			{
				Date scheduledate=rs.getDate(1);
				String votingdate=dateformat.format(scheduledate);
				System.out.print(votingdate);
				
				if(votingdate.compareTo(currentdate)==0)
				{
					
					System.out.print("today is voting date");
					response.sendRedirect("VotingPage.jsp");
		            break;
				}
				else
				{
					  
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('ERROR !', 'Today is not Voting Day', 'error');");
		            out.println("});");
		            out.println("</script>");
		            RequestDispatcher rd = request.getRequestDispatcher("Dashboard.html");
		            rd.include(request, response);
		            return;
		            
		            
		           
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
