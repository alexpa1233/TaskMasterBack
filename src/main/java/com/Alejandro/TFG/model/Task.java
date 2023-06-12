package com.Alejandro.TFG.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

//Valores de la tabla Task
@Data
@NoArgsConstructor
@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    
    @Column(nullable=false)
    private String description;
    
    @Column(name = "due_date")
    private LocalDate dueDate;
    
    @Column(nullable=false)
    private String status;
    
    @Column(nullable=false)
    private String priority;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
