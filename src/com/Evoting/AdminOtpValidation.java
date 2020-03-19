package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminOtpValidation
 */
@WebServlet("/AdminOtpValidation")
public class AdminOtpValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession(false);
		String emailid=(String) session.getAttribute("emailid1");
		int otp= (int) session.getAttribute("otp1");
		
		PrintWriter out=response.getWriter();
		
		int enteredotp=Integer.parseInt(request.getParameter("otp"));
				if(otp==enteredotp)
				{
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
						Statement stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery("select * from admin");
						
						while(rs.next())
						{
							if(emailid.equals(rs.getString(1)))
							{
								//RequestDispatcher dispatcher=request.getRequestDispatcher("AdminDashboard.html");
								//dispatcher.forward(request,response);
								response.sendRedirect("AdminDashboard.html");
							}
						}
					}
					catch(Exception p)
					{
						p.printStackTrace();
					}
					
					//response.sendRedirect("Dashboard.html");
				}
				else
				{
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('ERROR !', 'You Entered Wrong OTP...Try Again', 'error');");
		            out.println("});");
		            out.println("</script>");
		            
		           RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
		           rd.include(request, response);
					//out.print("invalid otp...");
				}
	}

}
