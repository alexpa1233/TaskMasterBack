package com.Alejandro.TFG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Alejandro.TFG.model.User;
import com.Alejandro.TFG.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
public class ImageController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/image/upload")
    public ResponseEntity<String> handleImageUpload(@RequestParam("image") MultipartFile image,  @RequestParam("userId") Long userId) {
        
        // Verifica si el archivo está vacío
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Imagen vacía");
        }

        try {
            //Obten los bytes de la img
            byte[] bytes = image.getBytes();

           
            String serverPath = "";//Poner ruta de donde se guardara las img

            // Nombre único para la imagen (puedes usar una lógica más robusta)
            String fileName = image.getOriginalFilename();

            // Crea el archivo en el servidor
            File serverFile = new File(serverPath + fileName);
            image.transferTo(serverFile);

            // Guardar información en la base de datos
        Optional<User> optionalUsuario = userRepository.findById(userId);

            if (optionalUsuario.isPresent()) {
                User user = optionalUsuario.get();
                user.setPhotoLocation(serverFile.getAbsolutePath());

                

                userRepository.save(user);

                return ResponseEntity.ok("Imagen y datos del usuario guardados correctamente");
            }

            return ResponseEntity.ok("Imagen guardada correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
    }
}
