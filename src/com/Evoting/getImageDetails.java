package com.Evoting;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getImageDetails
 */
@WebServlet("/getImageDetails")
public class getImageDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int img_id = Integer.parseInt(request.getParameter("emailid"));
		
		
		 
	 
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			PreparedStatement pstmt = con.prepareStatement("select Photo from voters wher Email_Id=?");
		pstmt.setInt(1, img_id);
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
		
	}

	

}
