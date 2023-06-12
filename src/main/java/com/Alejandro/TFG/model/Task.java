package com.Alejandro.TFG.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;
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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TaskType type;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
