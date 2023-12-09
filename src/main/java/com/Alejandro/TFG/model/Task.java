package com.Alejandro.TFG.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

//Valores de la tabla Task
@Data
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(nullable=false)
    private String title;
    
    @Column(nullable=false)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TaskType type;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    @JsonIgnoreProperties(value="task")
    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Social social;

    @JsonIgnoreProperties(value="task")
    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Work work;
    
}
