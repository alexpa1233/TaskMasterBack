package com.Alejandro.TFG.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

//Valores de la tabla Notification

@Data
@NoArgsConstructor
@Entity
@Table(name = "Work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "Work")
    private List<WorkCheckBox> workCheckBox;

    

}

