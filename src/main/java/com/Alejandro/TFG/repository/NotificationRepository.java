package com.Alejandro.TFG.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.Notification;
import com.Alejandro.TFG.model.Task;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long>{
    List<Notification> findByNotificationDate(LocalDate date);

    List<Notification> findByTaskAndActiveOrNotificationDateNotNull(Long taskId, boolean active);

    List<Notification> findByTaskAndActive(Task task, boolean active);
}
