package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		String emailid=request.getParameter("emailid");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
				boolean flag=false;
				
		int otp=new Random().nextInt(900000)+100000;
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from admin");
			
			while(rs.next())
			{
				if(emailid.equals(rs.getString(1))&& password.equals(rs.getString(2)))
				{
					session.setAttribute("emailid1", emailid);
					session.setAttribute("otp1", otp);
					flag=true;
					SendOtp.send(emailid,otp);
					response.sendRedirect("AdminLoginEnterOtp.html");
				}
			}
			if(flag==false)
			{
				//out.print("invalid emailid or password");
				//response.sendError(1," You Entered Wrong OTP.");
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
	            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
	            out.println("<script>");
	            out.println("$(document).ready(function(){");
	            out.println("swal('ERROR !', 'Wrong Email id or Password....Try Again', 'error');");
	            out.println("});");
	            out.println("</script>");
	            RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
	            rd.include(request, response);
			}
		}
		catch(Exception p)
		{
			out.print(p);
		}
		
		
	}

}
