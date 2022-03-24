package com.example.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		String email ="zavacxva@gmail.com";
		String pass = "ftbrdphbwmbcmuzk";

		return new PasswordAuthentication(email,pass);
	}
}