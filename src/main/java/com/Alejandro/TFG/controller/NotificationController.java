/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.NotificationService;
import com.Alejandro.TFG.model.NotificationDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Alex
 */

public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    

    @PostMapping
    public ResponseEntity<NotificationDB> createNotification(@RequestBody NotificationDB notification) {
        NotificationDB createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationDB> getNotificationById(@PathVariable Long notificationId) {
        NotificationDB notification = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notification);
    }
}
