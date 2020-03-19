package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		//HttpSession session=request.getSession(false);
		
		
			response.setHeader("Cache-Control","no-store,must-revalidate");
			response.setDateHeader ("Expires", 0);
			RequestDispatcher rd = request.getRequestDispatcher("Dashboard.html");
            rd.forward(request, response);
			
	}
		
	

	
	
}
