<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.io.*,java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voting Result</title>
<link rel="stylesheet" href="Dashboardstyle.css">
<link rel="stylesheet" href="GetResultPage.css">
<style>
.backbutton
{
	border:none;
	outline:none;
	height:40px;
	width:200px;
	background:#fb2525;
	color:#fff;
	font-size:18px;
	border-radius:80px;
	position:fixed;
	margin:auto;
	margin-left:auto;
	margin-right:auto;
	left:50%;
	top:85%;
}
.backbutton:hover
{
   cursor:pointer;
   background:#ffc107;
   color:#000;
   
}
h3
{
	font-family:times new roman;
	text-decoration:underline;
	color:blue;
	

}
</style>
</head>
<body>
<div class="wrapper">
    <div class="sidebar">
        <h2>Welcome To E-Voting Portal</h2>
        <ul>
            <li><a href="HomePage.html"><i class="fas fa-home"></i>Home</a></li>
            <li><a href="#"><i class="fas fa-user"></i>Profile</a></li>
            <li><a href="#"><i class="fas fa-address-card"></i>About</a></li>
        </ul> 
        
    </div>
    <div class="main_content">
        <div class="header">Welcome!! Have a nice day Voter.</div> 
        <img left="50px" src="logo.png" class="logo"></img> 
        <h1>Together We will Make Our State Great Again....</h1>
    
<%

try
{
	int cnt=1;
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select PartyName,MAX(TotalVotes) as TotalVotes from votecount");
	
	
	while(rs.next())
	{

	
		%><br><br><marquee><h2 align="center">Winner is <%=rs.getString(1)%> With the total vote count of <%=rs.getInt(2)%></h2></marquee>
		<h3 align="center" >Voting Result</h3>
		<% 
	}
	
	ResultSet rs1=stmt.executeQuery("select * from votecount");
	

		%>
		<table align="center" cellpadding="150" border="1" style="text-align:center;">
		<% 	ResultSetMetaData rsmd=rs1.getMetaData();  
			//int total=rsmd.getColumnCount();  
			out.print("<tr>");  
			out.print("<th>Sr No.</th>");
			for(int i=1;i<=2;i++)  
			{  
			out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
			}  
			
			while (rs1.next()) 
			{
	
		
				%>
				<tr>
				<td><%=cnt %></td>
				<td><%=rs1.getString(1)%></td>
				<td><%=rs1.getInt(2)%></td>
				<%cnt++; %>
				</tr>
				
				<%
			} 
		%>
		</table>
		<br>
		<br>
		<button class="backbutton" align="center" onclick=location.href="Dashboard.html">Back</button>
		<% 
		
	
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
</body>
</html>