package com.project.shareMed.model;

import com.sun.istack.NotNull;

public class EmailModel {
	
	@NotNull
	private String email;
	private String emailBody;
	private String emailSubject;
	
	
	
	public EmailModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public EmailModel(String email, String emailBody, String emailSubject) {
		super();
		this.email = email;
		this.emailBody = emailBody;
		this.emailSubject = emailSubject;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getEmailBody() {
		return emailBody;
	}



	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}



	public String getEmailSubject() {
		return emailSubject;
	}



	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
		
	
	
}
