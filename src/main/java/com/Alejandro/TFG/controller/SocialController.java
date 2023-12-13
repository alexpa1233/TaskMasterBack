package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.SocialService;
import com.Alejandro.TFG.model.Social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social/")
public class SocialController {

    @Autowired
    private SocialService socialService;

    @GetMapping("/{id}")
    public ResponseEntity<Social> getSocialById(@PathVariable Long id) {
        Social social = socialService.getSocialById(id);
        return new ResponseEntity<>(social, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Social>> getAllSocials() {
        List<Social> socials = socialService.getAllSocials();
        return new ResponseEntity<>(socials, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Social> saveSocial(@RequestBody Social social) {
        Social createdSocial = socialService.saveSocial(social);
        return new ResponseEntity<>(createdSocial, HttpStatus.CREATED);
    }

    

    @PutMapping("/{socialId}")
    public ResponseEntity<Social> updateTask(@PathVariable Long socialId, @RequestBody Social updatedSocial) {
        Social social = socialService.getSocialById(socialId);

        if (social != null) {
            
            // Actualizar las propiedades de la tarea existente con las del objeto actualizado
            social.setHourAdvise(updatedSocial.getHourAdvise());
            

            // Guardar la tarea actualizada
           Social updatedInputSocial = socialService.saveSocial(social);

            return ResponseEntity.ok(updatedInputSocial);
        } else {
            // La tarea no existe
            return ResponseEntity.notFound().build();
        }
    }
}