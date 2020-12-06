package com.Evoting;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendOtp {
	public static void send(String to,int otp) 
	{
		Properties props=new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable", "true");
		
		 Session session=Session.getInstance(props, new javax.mail.Authenticator()
				 {
			 		protected PasswordAuthentication getPasswordAuthentication()
			 		{
			 			return new PasswordAuthentication("EMAILID@gmail.com","PASSWORD");
			 		}
				 });
		 try
		 {
			 MimeMessage message=new MimeMessage(session);
			 message.setFrom(new InternetAddress("shubhamkakade061@gmail.com"));
			 message.addRecipient(RecipientType.TO, new InternetAddress(to));
			 message.setText("Your One time Password is "+otp+".Please Enter following OTP To Login into Evoting Portal. ");
	         message.setSubject("Evoting System");
	         
	         Transport.send(message);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

	}

}

