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


@WebServlet("/VerifyVoter")
public class VerifyVoter extends HttpServlet {
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
			out.print("<img  src=\"logo.png\" class=\"logo\"></img>");
			out.print("<table width=50% border=1 align=center>");  
			out.print("<h1 align='center'>Voters List</h1>"); 
			out.print("<br><br>");
			out.print("<button class=\"verifiedbackbutton\"  onclick=\"location.href='./VerifiedVoterList'\">View Verified Voters</button> <button class=\"unverifiedbackbutton\" onclick=\"location.href='./UnverifiedVoterList'\">View UnVerified Voters</button>");
			out.print("<br><br><br>");
			ResultSetMetaData rsmd=rs.getMetaData();  
			//int total=rsmd.getColumnCount();  
			out.print("<tr>");  
			for(int i=1;i<=7;i++)  
			{  
			out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
			}  
			out.print("<th>"+rsmd.getColumnName(10)+"</th>"); 
			out.print("<th>"+rsmd.getColumnName(12)+"</th>");
			out.print("<th> Verify   </th>");
			
			out.print("</tr>");  
			while(rs.next())  
				
			{  
				
			out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td><a href=./ViewPhoto?emailid="+rs.getString(7)+">View </a></td><td><a href=./ViewAadharcard?emailid="+rs.getString(7)+">View </a></td><td><a href=./VerifyVoterInfo?emailid="+rs.getString(7)+">Verify </a></td></tr>");  
			                  
			}  
			out.print("</table><br><br>"); 
			
			out.print("<button class=\"backbutton\" onclick=\"location.href='http://localhost:8081/Evoting/AdminDashboard.html'\">Back</button>");
			out.print("<br><br><br>");
			
			out.println("</body></html>");
            response.setHeader("Cache-Control", "private, no-store, no-cache,must-revalidate");
			 response.setHeader("Pragma", "no-cache");
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
