package com.Alejandro.TFG.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Valores de la tabla Notification

@Data
@NoArgsConstructor
@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "work")
    private List<Step> steps;

    @OneToOne
    @JsonIgnoreProperties(value="work")
    @JoinColumn(name = "task_id")
    private Task task;

}

