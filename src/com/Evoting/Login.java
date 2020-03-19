
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


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
				boolean flag=false;
				
		int otp=new Random().nextInt(900000)+100000;
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from voters");
			
			while(rs.next())
			{
				if(userid.equals(rs.getString(8))&& password.equals(rs.getString(9)))
				{
					flag=true;
					if(rs.getString(11)!=null)
					{
						session.setAttribute("emailid", userid);
						session.setAttribute("otp", otp);
						flag=true;
						SendOtp.send(userid,otp);
	
						response.sendRedirect("EnterOtp.html");	
					}
					else
					{
						out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			            out.println("<script>");
			            out.println("$(document).ready(function(){");
			            out.println("swal('ERROR !', 'Your Account Is Under Verification .....Please Try Again Later', 'error');");
			            out.println("});");
			            out.println("</script>");
			            RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
			            rd.include(request, response);
			           
					}
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
	           // response.sendRedirect("HomePage.html");
			}
		}
		catch(Exception p)
		{
			out.print(p);
		}
	
	}

}
