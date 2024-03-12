package com.Alejandro.TFG.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class StepDTO {
    private Long id;
    private String name;
    private boolean active;

    public StepDTO(Long id, String name, boolean active){
        this.id = id;
        this.name = name;
        this.active = active;
        
    }
    
    public StepDTO(){

    }
}
