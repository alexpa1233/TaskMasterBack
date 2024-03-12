package com.Alejandro.TFG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Alejandro.TFG.Service.EmailService;
import com.Alejandro.TFG.model.User;

@RestController
@RequestMapping("/api/email/")
public class EmailController {
    
    @Autowired
    EmailService emailService;
    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody User user){
        emailService.sendEmail(user);
        return ResponseEntity.ok("Enviado correctamente el correo");
       
    }
}
