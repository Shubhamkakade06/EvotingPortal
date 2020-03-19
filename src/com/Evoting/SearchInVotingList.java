package com.Evoting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/SearchInVotingList")
public class SearchInVotingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();  
		String cssTag="<link rel='stylesheet' type='text/css' href='table.css'>";
		String emailid=request.getParameter("emailid");  
	   
			if(emailid=="" || emailid==null)
			{
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
	            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
	            out.println("<script>");
	            out.println("$(document).ready(function(){");
	            out.println("swal('ERROR !', 'Please Enter Email id....Try Again', 'error');");
	            out.println("});");
	            out.println("</script>");
	            RequestDispatcher rd = request.getRequestDispatcher("SearchInVotingList.html");
	            rd.include(request, response);
			}
			else
			{
		
					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
							PreparedStatement ps=con.prepareStatement("select * from voters where Email_Id=?");  
							ps.setString(1,emailid); 
							ResultSet rs=ps.executeQuery();  
							out.println("<html>");
							out.println("<head><title>Voter Details</title>"+cssTag+"</head>");
							out.println("<body>");
							out.print("<img align=\"center\" src=\"logo.png\" class=\"logo\"></img>");
							out.print("<table width=50% border=1 align=center>");  
							out.print("<h1 align='center'>Voters Details</h1>"); 
							ResultSetMetaData rsmd=rs.getMetaData();  
							//int total=rsmd.getColumnCount();  
							out.print("<tr>");  
							for(int i=1;i<=7;i++)  
							{  
							out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
							}  
							out.print("<th>"+rsmd.getColumnName(10)+"</th>");   
							out.print("</tr>");
							
							while(rs.next())  
							{  
							out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td><a href=./ViewPhoto?emailid="+rs.getString(7)+">View </a></td></tr>");  
							                 
							}  
							out.print("</table>");  
							out.println("<br><br>");
				             out.println("<button class=\"backbutton\" align=\"center\" onclick=\"location.href='SearchInVotingList.html'\">Back</button>");
				             
							out.println("</body></html>");
							
					}
					catch(Exception e2) 
					{
							e2.printStackTrace();
					}  
			}
			
					              
	}
		

}
