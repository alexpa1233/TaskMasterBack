package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.NotificationDB;
import java.time.LocalDateTime;






public interface NotificationService {
    
    NotificationDB createNotification(NotificationDB notification);
    NotificationDB getNotificationById(Long notificationId);
    void cancelNotification(Long notificationId);
    void sendNotification(Long notificationId);
    
}
