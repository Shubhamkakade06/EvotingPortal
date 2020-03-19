package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ViewVoterList")
public class ViewVoterList extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter(); 
		String cssTag="<link rel='stylesheet' type='text/css' href='table.css'>";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from voters");
			out.println("<html>");
			out.println("<head><title>VoterList</title>"+cssTag+"</head>");
			out.println("<body>");
			out.print("<img align=\"center\" src=\"logo.png\" class=\"logo\"></img>");
			out.print("<table width=50% border=1 align=center>");  
			out.print("<h1 align='center'>Voters List</h1>"); 
			
			
			ResultSetMetaData rsmd=rs.getMetaData();  
			//int total=rsmd.getColumnCount();  
			out.print("<tr>");  
			for(int i=1;i<=6;i++)  
			{  
			out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
			}  
			  
			out.print("</tr>");  
			while(rs.next())  
			{  
			out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getString(6)+"</td></tr>");  
			                  
			}  
			out.print("</table><br><br>");  
			 out.println("<button  class=\"backbutton\" onclick=location.href='AdminDashboard.html'>Back</button>");
            out.println("</body></html>");
		}
		catch(Exception e2) 
		{
			e2.printStackTrace();
		}  
		          
		finally
		{
			out.close();
			
		}  
		  
			
		
	}
}


