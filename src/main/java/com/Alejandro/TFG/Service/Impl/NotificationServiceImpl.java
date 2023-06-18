/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;

import com.Alejandro.TFG.Service.NotificationService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.NotificationDB;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.repository.NotificationRepository;
import com.Alejandro.TFG.repository.TaskRepository;

import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *
 * @author Alex
 */
@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private  NotificationRepository notificationRepository;

   

    
     @Override
    public NotificationDB getNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    @Override
    public NotificationDB createNotification(NotificationDB notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void cancelNotification(Long notificationId) {
        NotificationDB notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        notification.setActive(false);
        notificationRepository.save(notification);
    }

    @Override
    public void sendNotification(Long notificationId) {
        NotificationDB notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        if(!notification.isActive()){
            return;
        }

        // Obtener los datos de la notificación primordiales para el envio de la notificcion
        String title = notification.getTask().getTitle() + " - Notificación";
        String message = calculateRemainingTimeMessage(notification.getNotificationDate(), notification.getRecurringInterval());

        
        
        
        // Construir el mensaje de la notificación
        Message firebaseMessage = Message.builder()
                .setNotification(Notification
                .builder()
                .setTitle(title)
                .setBody(message)
                .build()
                )
                .build();

        try {
            // Enviar la notificación utilizando Firebase Cloud Messaging
           String response = FirebaseMessaging.getInstance().send(firebaseMessage);
            LoggerFactory.getLogger(NotificationServiceImpl.class).info("Successfully sent message: {}", response);
        } catch (Exception e) {
            LoggerFactory.getLogger(NotificationServiceImpl.class).error("Error sending message: {}", e.getMessage());
        }
    }
    
    //Esta funcion se encarga de generar los mensajes 
    private String calculateRemainingTimeMessage(LocalDateTime localDateTime, String recurringInterval) {
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();

        // Calcular el intervalo restante hasta la fecha objetivo en función del recurringInterval
        long remainingIntervals = 0;
        String intervalUnit = "";

        switch (recurringInterval) {
            case "diariamente":
                remainingIntervals = ChronoUnit.DAYS.between(currentDate, localDateTime);
                intervalUnit = "día(s)";
                break;
            case "cada_hora":
                remainingIntervals = ChronoUnit.HOURS.between(currentDate.atStartOfDay(), localDateTime.now());
                intervalUnit = "hora(s)";
                break;
            case "semanalmente":
                remainingIntervals = ChronoUnit.WEEKS.between(currentDate, localDateTime);
                intervalUnit = "semana(s)";
                break;
            case "mensualmente":
                remainingIntervals = ChronoUnit.MONTHS.between(currentDate, localDateTime);
                intervalUnit = "mes(es)";
                break;
            
            default:
                throw new IllegalArgumentException("Intervalo de notificación no válido: " + recurringInterval);
        }

        // Construir el mensaje de tiempo restante
        String intervalMessage;
        if (remainingIntervals > 0) {
            intervalMessage = "Faltan " + remainingIntervals + " " + intervalUnit;
        } else {
            intervalMessage = "La fecha objetivo ha llegado";
        }

        return intervalMessage + " para completar la tarea.";
    }
}
