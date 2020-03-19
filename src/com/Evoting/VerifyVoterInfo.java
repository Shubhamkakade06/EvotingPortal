package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VerifyVoterInfo")
public class VerifyVoterInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String emailid=request.getParameter("emailid");
		PrintWriter out=response.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement stmt=con.prepareStatement("select Verified from voters Where Email_Id=?");
			stmt.setString(1, emailid);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString(1)==null)
				{
					PreparedStatement stmt1=con.prepareStatement("update  voters set Verified=\"Verified\" Where Email_Id=?");
					stmt1.setString(1, emailid);
					int i=stmt1.executeUpdate();
					System.out.println(i+" Rows Updated");
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('SUCCESS !', 'Account Verified Successfully', 'success');");
		            out.println("});");
		            out.println("</script>");
		            RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.html");
		            rd.include(request, response);
				}
				else
				{
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('SUCCESS !', 'Account Already Verified', 'success');");
		            out.println("});");
		            out.println("</script>");
		            RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.html");
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
