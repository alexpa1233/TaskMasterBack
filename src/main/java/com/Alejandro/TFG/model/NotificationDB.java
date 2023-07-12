package com.Alejandro.TFG.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

//Valores de la tabla Notification

@Data
@NoArgsConstructor
@Entity
@Table(name = "Notification")
public class NotificationDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "recurring_interval")
    private RecurringInterval recurringInterval;
    
    @Column(nullable=false)
    private boolean active;

    
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}

