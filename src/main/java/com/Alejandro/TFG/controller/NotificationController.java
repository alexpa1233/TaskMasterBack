/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.NotificationService;
import com.Alejandro.TFG.model.NotificationDB;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alex
 */

public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    private Timer timer = new Timer();

     private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    

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

    @PostMapping("/send")
    public void sendNotification(@RequestParam("notificationId") Long notificationId) {
        notificationService.sendNotification(notificationId);
    }

     @PostMapping("/startTimer")
    public void startNotificationTimer(@RequestParam("notificationId") Long notificationId, @RequestParam("interval") long interval) {
        timer.schedule(new NotificationTask(notificationId), interval, interval);
    }

    @PostMapping("/startTimer")
    public void startNotificationTimer(@RequestParam("notificationId") Long notificationId, @RequestParam("hour") int hour, @RequestParam("minute") int minute) {
        // Obtener la hora actual
        LocalTime now = LocalTime.now();
        
        // Calcular la diferencia de tiempo hasta la hora especificada
        long initialDelay = calculateInitialDelay(now, hour, minute);
        
        // Programar la tarea de envío de notificaciones diariamente a la hora especificada
        executorService.scheduleAtFixedRate(() -> notificationService.sendNotification(notificationId), initialDelay, 24, TimeUnit.HOURS);
    }



    private long calculateInitialDelay(LocalTime now, int targetHour, int targetMinute) {
        LocalTime targetTime = LocalTime.of(targetHour, targetMinute);
        if (targetTime.isBefore(now)) {
            targetTime = targetTime.plusHours(24);
        }
        return Duration.between(now, targetTime).toMillis();
    }


    //Esta clase esta hecha para enviar las notificaciones automaticamente

    private class NotificationTask extends TimerTask {
        private final Long notificationId;

        public NotificationTask(Long notificationId) {
            this.notificationId = notificationId;
        }

        @Override
        public void run() {
            notificationService.sendNotification(notificationId);
        }
    }
}
