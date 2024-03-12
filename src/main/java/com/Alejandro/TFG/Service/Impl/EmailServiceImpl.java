package com.Alejandro.TFG.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Alejandro.TFG.Service.EmailService;
import com.Alejandro.TFG.model.User;

@Service
public class EmailServiceImpl implements EmailService{
    
    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(User user){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("taskmastertfg01@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Recovery Password");
        message.setText("Your password is " + user.getPassword());

        javaMailSender.send(message);
    }

}
