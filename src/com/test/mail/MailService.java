package com.test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailService {
  private static String UNAME = "vikas6sharma@gmail.com";
  private static String PASSWORD = "";
  public static boolean sendMail(String to , String sub , String data){
	  try{
	  Message msg = getMessageForMail();
	  msg.setSubject(sub);
	  msg.setFrom(new InternetAddress(UNAME, "Vikas K"));
	  msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	  msg.setRecipient(Message.RecipientType.BCC, new InternetAddress("manikantkr@gmail.com"));
	  msg.setSentDate(new Date());
	  msg.setContent(data, "text/html");
	  Transport.send(msg);
	  }catch(Exception e){
		  System.out.println("incorrect Credential");
		  e.printStackTrace();
		  return false;
	  }
	  return true;
  }
  
  private static Message getMessageForMail(){
	  Authenticator auth = new MyPasswordAuthenticator();
	  Properties p = new Properties();
	  p.put("mail.smtp.host", "smtp.gmail.com");
	  p.put("mail.smtp.socketFactory.port", "465");
	  p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	  p.put("mail.smtp.auth", "true");
	  p.put("mail.smtp.port", "465");
	  Session sess = Session.getDefaultInstance(p, auth);
	  Message msg = new MimeMessage(sess);
	  return msg;
	  
  }
  
 static class MyPasswordAuthenticator extends Authenticator{
		
		protected PasswordAuthentication getPasswordAuthentication(){
			System.out.println("\n-- getPasswordAuthentication() called--");
			return new PasswordAuthentication(UNAME, PASSWORD);
		}
	}
}

