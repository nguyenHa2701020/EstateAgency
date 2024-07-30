package com.edu.estate_agency.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class EmailUlti {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String desAddress, String title, String content) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(desAddress);
            simpleMailMessage.setSubject(title);
            simpleMailMessage.setText(content);
            mailSender.send(simpleMailMessage);
        } catch (MailException mailException) {
            mailException.printStackTrace();
        }
    }
}
