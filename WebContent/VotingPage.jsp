<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voting Page</title>
<link rel="stylesheet" href="Dashboardstyle.css">
<link rel="stylesheet" href="VotingPageTable.css">
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
	 margin:50px auto;
  display:block;
  radius:2px;
}
.backbutton:hover
{
   cursor:pointer;
   background:#ffc107;
   color:#000;
   
}
.logo
{
	width:600px;
  display: block;
  margin-left: 25%;
  margin-right: auto;
 

}
body
{
bgcolor:white;
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
        <img  src="logo.png" class="logo"></img>
        <div class="header">Welcome!! Have a nice day Voter.</div>  
        <h1>Together We will Make Our State Great Again....</h1>
    
<%

try
{
	String emailid=request.getParameter("emailid");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("select * from candidates");
	%>
	<table  border="1" style="text-align:center;">
	<% 	ResultSetMetaData rsmd=rs.getMetaData();  
			//int total=rsmd.getColumnCount();  
			out.print("<tr>");  
			for(int i=1;i<=4;i++)  
			{  
			out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
			}  
			out.print("<th>Give Vote</th>");  
			out.print("</tr>");%>
	<%
	while (rs.next()) 
	{
	
		
	%>
	<tr>
	<td><%=rs.getString(1)%></td>
	<td><%=rs.getString(2)%></td>
	<td><img src="getImageDetails.jsp?candidatename=<%=rs.getString(1) %>" width="70" height="70"></img></td>
	<td><img src="getPartyPhoto.jsp?candidatename=<%=rs.getString(1) %>" width="70" height="70"></img></td>
	<td><a href="./SubmitVote?partyname=<%=rs.getString(2)%>"><button class="button">Vote</button></a></td>
	</tr>
	<%} %>
	<%-- <tr>
	<td colspan="3">None Of The Above</td>
	<td><img src="nota.jpg"width="70" height="70"></img></td>
	<td><button class="button"  onClick="./ViewVoterList">Vote</button></td>
	</tr>--%>

<%	
}
catch(Exception e)
{
	e.printStackTrace();
}

%>
</table>
<button class="backbutton"  onClick="location.href='http://localhost:8081/Evoting/Dashboard.html'">Back to DashBoard</button>
</div>

</div>

</body>
</html>