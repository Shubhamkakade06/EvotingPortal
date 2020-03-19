<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voting Schedule</title>

<link rel="stylesheet" href="Dashboardstyle.css">
<style type="text/css">
h1 
{ 
	 color: #7c795d;
	 font-family: 'Trocchi', serif; 
	
	 font-size: 45px; 
	 font-weight: normal; 
	 line-height: 48px; 
	 margin: 0; 
	 text-align:center;
}
.h2
{
	font-family:"times new roman";
	text-align:center;
	align:center;
	size:60px;
	
	
}
.logo
{
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;

}
button
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
	left:50%;
}
button:hover
{
   cursor:pointer;
   background:#ffc107;
   color:#000;
   
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
	ResultSet rs=stmt.executeQuery("select * from Votingschedule");
	
	while(rs.next())
	{
		if(rs.last())
		{
		%>
		<br><br>
		<h2 class="h2">The latest Voting Scheduled on <%=rs.getDate(1) %></h2>
		<br>
		<br>
		
		<button class="button" align="center" onclick=location.href="Dashboard.html">Back</button>
		<% 
		}
		
	}
}
catch(Exception e)
{
	e.printStackTrace();
}
	
	%>
</body>
</html>