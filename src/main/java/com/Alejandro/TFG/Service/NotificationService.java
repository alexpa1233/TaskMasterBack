package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.Notification;
import java.time.LocalDateTime;






public interface NotificationService {
    
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long notificationId);
    void cancelNotification(Long notificationId);
    void sendNotification(Long notificationId);
    
}
