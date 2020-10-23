package com.mortgage.loans.api.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
	
	 @Autowired
	 private JavaMailSender sender;
	 
	public String email(String subject, String email, String msg) {
		String resp = Constants.FAILED;
		
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setText(msg);
            helper.setSubject(subject);
            resp = "Email Sent";
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
		return resp;
	}

}
