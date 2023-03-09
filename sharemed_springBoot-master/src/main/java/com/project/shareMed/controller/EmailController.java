package com.project.shareMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.shareMed.model.EmailConfig;
import com.project.shareMed.model.EmailModel;

@RestController
@RequestMapping("/sendEmail")
public class EmailController {
	
	public EmailConfig emailConfig;
	public EmailController(EmailConfig emailConfig) {
		super();
		this.emailConfig = emailConfig;
	}
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostMapping
	public void sendEmail(@RequestBody EmailModel emailModel) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailConfig.getUserName());
		simpleMailMessage.setTo(emailModel.getEmail());
		simpleMailMessage.setSubject(emailModel.getEmailSubject());
		simpleMailMessage.setText(emailModel.getEmailBody());
		
		javaMailSender.send(simpleMailMessage);
		
		System.out.println("Mail Sent....");
	}
	
	
}
