package com.example.projectTestDemo.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

//    TODO : research set From email dynamic mail because but now use email from config
    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("peach.rawinan@gmail.com");
        message.setTo("boonyaris.p@aware.co.th");
        message.setSubject("test email");
        message.setText("hello world");
        javaMailSender.send(message);
    }

}
