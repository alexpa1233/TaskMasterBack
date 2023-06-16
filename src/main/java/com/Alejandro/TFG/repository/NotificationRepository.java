package com.Alejandro.TFG.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.NotificationDB;
import com.Alejandro.TFG.model.Task;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationDB,Long>{
    List<NotificationDB> findByNotificationDate(LocalDate date);

    List<NotificationDB> findByTaskAndActiveOrNotificationDateNotNull(Task task, boolean active);

    List<NotificationDB> findByTaskAndActive(Task task, boolean active);
}
