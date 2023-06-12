package com.Alejandro.TFG.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

//Valores de la tabla Notification
@Data
@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @Column(name = "recurring_interval")
    private String recurringInterval;
    
    @Column(nullable=false)
    private boolean active;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}

