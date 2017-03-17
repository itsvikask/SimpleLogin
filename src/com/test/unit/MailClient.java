package com.test.unit;

import com.test.mail.MailService;

public class MailClient {

	public MailClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String to = "vikas.k@hp.com";
		String sub = "test Mail";
		String data = "<font color = 'red' size ='6'> This is mail from hp</font>";
		MailService.sendMail(to, sub, data);
		System.out.println("***Mail Sent******");

	}

}
