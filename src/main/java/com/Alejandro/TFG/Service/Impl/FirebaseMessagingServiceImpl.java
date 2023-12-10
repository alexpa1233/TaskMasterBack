package com.Alejandro.TFG.Service.Impl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.Alejandro.TFG.Service.FirebaseMessagingService;
import com.Alejandro.TFG.exception.NotificationException;
import com.Alejandro.TFG.model.NotificationMessage;
import com.Alejandro.TFG.model.Social;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.TaskType;
import com.Alejandro.TFG.repository.TaskRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService{
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private TaskRepository taskRepository;

   

    private NotificationMessage createNotificationMessage(Task task) {
        // Lógica para construir el objeto NotificationMessage desde 'social'
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setRecipientToken(task.getUser().getDeviceId());
        notificationMessage.setTitle(task.getUser().getUsername());
        notificationMessage.setBody( task.getUser().getName() +" do your task " + task.getTitle() + " now");
        

        return notificationMessage;
    }


    @Override
    @Scheduled(fixedRate = 60000) // ejecutar cada minuto (ajusta según tus necesidades)
    public String verificarEnvioNotificacionesProgramadas() {
        // Lógica para verificar y enviar notificaciones programadas
        // Puedes llamar a shouldSendNotification y enviar notificaciones aquí

        List<Task> tasks = taskRepository.findByType(TaskType.SOCIAL); // Obtener todas las tareas

        if (tasks.isEmpty()) {
            throw new NotificationException("Don't exist any Task with type Social");
        }
        for (Task task : tasks) {
            if (task.getSocial() != null&& task.getType().equals(TaskType.SOCIAL) && shouldSendNotification(task.getSocial())) {
                // Construir y enviar notificación
                NotificationMessage notificationMessage = createNotificationMessage(task);
                Notification notification = Notification.builder()
                    .setTitle(notificationMessage.getTitle())
                    .setBody(notificationMessage.getBody())
                    .build();

                Message message = Message.builder()
                    .setToken(notificationMessage.getRecipientToken())
                    .setNotification(notification)
                    .putAllData(notificationMessage.getData())
                    .build();

                try {
                    firebaseMessaging.send(message);
                } catch (FirebaseMessagingException e) {
                    throw new NotificationException("Error Sending Notification", e);
                }
            } else {
                throw new NotificationException("Notification not sent due to time condition");
            }
        }
        throw new NotificationException("Don't exist any Task with type Social");
    }


    private boolean shouldSendNotification(Social social) {
        // Lógica para determinar si la notificación debe enviarse en función de la hora actual y hourAdvise
        // Debes ajustar esto según tus necesidades exactas
        if (social != null && social.getHourAdvise() != null) {
            LocalTime currentTime = LocalTime.now();
            return currentTime.isAfter(social.getHourAdvise());
        }
        return false;
    }
}
