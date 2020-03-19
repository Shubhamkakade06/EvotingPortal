package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;


@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		System.out.print("hello");
		String emailid=(String) session.getAttribute("emailid");
		String password=request.getParameter("password1");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement("update voters set Password=? where Email_Id=?");
			PreparedStatement pstmt1=(PreparedStatement) con.prepareStatement("update login set Password=? where Username=?");
			pstmt.setString(1, password);
			pstmt.setString(2, emailid);
			pstmt1.setString(1, password);
			pstmt1.setString(2, emailid);
			pstmt.executeUpdate();
			pstmt1.executeUpdate();
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal('SUCCESS !', 'Password Changed Successfully', 'SUCCESS');");
            out.println("});");
            out.println("</script>");
            
           RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
           rd.include(request, response);
		}
		catch(Exception p)
		{
			out.print("error");
		}
	}

}
