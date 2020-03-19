package com.Evoting;

import java.io.IOException;

import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		String emailid=request.getParameter("emailid");
		
		
		int otp=new Random().nextInt(900000)+100000;
		try
		{
			HttpSession session=request.getSession();
			session.setAttribute("emailid", emailid);
			session.setAttribute("otp", otp);
			ForgetPasswordSendOtp.send(emailid,otp);
			response.sendRedirect("ForgetPassword.html");
			//RequestDispatcher rd = request.getRequestDispatcher("./ValidateOtpForgetPassword");
	        //rd.include(request, response);
	      //response.sendRedirect("ForgetPassword.html");
		}
		catch(Exception p)
		{
			p.printStackTrace();
		}
		
	}

}
