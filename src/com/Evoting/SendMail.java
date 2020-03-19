package com.Evoting;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.*;
public class SendMail {

	public static void send(String to,String votingno) 
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
			 			return new PasswordAuthentication("evotingportal2019@gmail.com","Evoting@2019");
			 		}
				 });
		 try
		 {
			 MimeMessage message=new MimeMessage(session);
			 message.setFrom(new InternetAddress("evotingportal2019@gmail.com"));
			 message.addRecipient(RecipientType.TO, new InternetAddress(to));
			 message.setText("Congratulation....You are Successfully Register to Evoting Portal.Your Unique Voting Number is "+votingno+".Please note down it for future Reference ");
	         message.setSubject("Evoting System");
	         
	         Transport.send(message);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

	}

}
