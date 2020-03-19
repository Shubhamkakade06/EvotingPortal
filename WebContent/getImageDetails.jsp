<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String candidatename=request.getParameter("candidatename");




try {
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
	PreparedStatement pstmt = con.prepareStatement("select Candidate_Photo from candidates Where Candidate_Name=?");
pstmt.setString(1, candidatename);
ResultSet rs = pstmt.executeQuery();
    if(rs.next()) {
        byte barray[] = rs.getBytes(1);
        response.setContentType("image/gif");
    	OutputStream oImage=response.getOutputStream();
        oImage.write(barray);
        oImage.flush();
        oImage.close();
    }
}
catch(Exception ex){
   ex.printStackTrace();
}
%>
</body>
</html>