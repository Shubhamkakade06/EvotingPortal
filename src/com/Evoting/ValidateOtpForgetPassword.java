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
 * Servlet implementation class ValidateOtpForgetPassword
 */
@WebServlet("/ValidateOtpForgetPassword")
public class ValidateOtpForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		HttpSession session=request.getSession();
		String emailid=(String) session.getAttribute("emailid");
		int otp= (int) session.getAttribute("otp");
		
		PrintWriter out=response.getWriter();
		
		int enteredotp=Integer.parseInt(request.getParameter("otp"));
				if(otp==enteredotp)
				{ 
					RequestDispatcher dispatcher=request.getRequestDispatcher("ChangePassword.html");
					dispatcher.forward(request,response);
				}
				else
				{
					out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		            out.println("<script>");
		            out.println("$(document).ready(function(){");
		            out.println("swal('ERROR !', 'You Entered Wrong OTP..Please Try again Later', 'error');");
		            out.println("});");
		            out.println("</script>");
		           
				}
	}

}
