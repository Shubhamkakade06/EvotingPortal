<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*,java.text.ParseException,java.text.SimpleDateFormat,java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Voting Result</title>

<link rel="stylesheet" href="Dashboardstyle.css">
<link rel="stylesheet" href="GetResultPage.css">
<style type="text/css">
.backbutton
{
	border:none;
	outline:none;
	height:40px;
	width:200px;
	background:#fb2525;
	color:#fff;
	font-size:18px;

	margin-left:auto;
	margin-right:auto;
	top:80%;
}
.backbutton:hover
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
        <h1>Together We will Make Our State Great Again....</h1>
    
<%

try
{
	Date d=null;
	java.sql.Date date=null;
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
	
	Statement stmt=con.createStatement();
	Statement stmt2=con.createStatement();
	
	ResultSet rs=stmt.executeQuery("select PartyName,MAX(TotalVotes) as TotalVotes from votecount");
	
	
	
	while(rs.next())
	{
		
	PreparedStatement stmt1=con.prepareStatement("insert into result values(?,?,?)");
	stmt1.setString(1, rs.getString(1));
	stmt1.setInt(2, rs.getInt(2));
	stmt1.setDate(3, null);
	stmt1.executeUpdate();
	}
	
	ResultSet rs1=stmt.executeQuery("select * from votecount");
	

		%>
		<table align="center" cellpadding="150" border="1" style="text-align:center;">
		<% 	
			ResultSetMetaData rsmd=rs1.getMetaData();  
			//int total=rsmd.getColumnCount();  
			out.print("<tr>");  
			for(int i=1;i<=2;i++)  
			{  
			out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
			}  
			
			while (rs1.next()) 
			{
	
		
				%>
				<tr>
				<td><%=rs1.getString(1)%></td>
				<td><%=rs1.getInt(2)%></td>
				
				</tr>
			<% 
			} 
		
		
	
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
</body>
</html>