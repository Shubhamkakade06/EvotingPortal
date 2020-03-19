package com.Evoting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AddPartyInfo")
public class AddPartyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession(false);
		String partyname=request.getParameter("partyname");
		String presidentofparty=request.getParameter("partypresident");
		String totalmembers=request.getParameter("totalmembers");
		String filename=request.getParameter("partysymbol");
		PrintWriter out=response.getWriter();
		
		if(partyname.isEmpty()|| partyname==null || presidentofparty.isEmpty()|| totalmembers.isEmpty() || totalmembers==null )
		{
			out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal('ERROR !', 'All Fields are manadatory....Try Again', 'error');");
            out.println("});");
            out.println("</script>");
            
            
            
            
           RequestDispatcher rd = request.getRequestDispatcher("AddPartyInfo.html");
           rd.include(request, response);
           response.setHeader("Cache-Control", "private, no-store, no-cache,must-revalidate");
			 response.setHeader("Pragma", "no-cache");
			
		}
		else
		{
			try
			{
				File image=new File(filename);
				FileInputStream fis=null;
				System.out.println("errotr1");
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
				System.out.println("errotr2");
				PreparedStatement pstmt=con.prepareStatement("insert into partyinfo values(?,?,?,?)");
				pstmt.setString(1, partyname);
				pstmt.setString(2, presidentofparty);
				pstmt.setString(3, totalmembers);
				fis=new FileInputStream(image);
				pstmt.setBinaryStream(4, (InputStream) fis, (int)(image.length()));
				int count=pstmt.executeUpdate();
				System.out.println("errotr3");
				//Statement stmt=con.createStatement();
				//stmt.executeUpdate("insert into partyinfo values('"+partyname+"','"+presidentofparty+"','"+totalmembers+"','"+filename+"')");
				if(count>0)
				{
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
	            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
	            out.println("<script>");
	            out.println("$(document).ready(function(){");
	            out.println("swal('SUCCESS !', 'Congratulation..You are Successfully Registered', 'success');");
	            out.println("});");
	            out.println("</script>");
				}
	           
	            RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
	            rd.include(request, response);
	            response.setHeader("Cache-Control", "private, no-store, no-cache,must-revalidate");
				 response.setHeader("Pragma", "no-cache");
				
			}
			catch(Exception p)
			{
				p.printStackTrace();
			}
		}
			
	}

}
