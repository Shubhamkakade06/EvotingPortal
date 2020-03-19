package com.Evoting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAadharcard")
public class ViewAadharcard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String emailid=request.getParameter("emailid");
		byte[] img=null;
		ServletOutputStream sos=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement stmt=con.prepareStatement("select AadharCard from voters Where Email_Id=?");
			stmt.setString(1, emailid);
			ResultSet rs=stmt.executeQuery();
			System.out.println("gfhjkj");
			if(rs.next())
			{
				img=rs.getBytes(1);
			}
			sos=response.getOutputStream();
			sos.write(img);
			System.out.println("gfhjkj");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}

	

}
