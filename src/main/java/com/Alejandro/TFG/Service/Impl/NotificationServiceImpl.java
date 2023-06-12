/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;

import com.Alejandro.TFG.Service.NotificationService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.Notification;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.repository.NotificationRepository;
import com.Alejandro.TFG.repository.TaskRepository;

import java.time.LocalDateTime;

/**
 *
 * @author Alex
 */
public class NotificationServiceImpl implements NotificationService{
    
    private final NotificationRepository notificationRepository;
    private final TaskRepository taskRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, TaskRepository taskRepository) {
        this.notificationRepository = notificationRepository;
        this.taskRepository = taskRepository;
    }
     @Override
    public Notification getNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void cancelNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        notification.setActive(false);
        notificationRepository.save(notification);
    }

    @Override
    public void sendNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        Task task = notification.getTask();
        
        //TODO: All about send notification with firebase/ hacer todo lo relacionado con enviar la notificacion con firebase
    }
    
}
