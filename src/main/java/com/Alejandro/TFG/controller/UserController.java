/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.UserService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.exception.NotLoginException;
import com.Alejandro.TFG.model.LoginRequest;
import com.Alejandro.TFG.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private  UserService userService;

    
    
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        
        User existingUser = userService.getUserById(userId);

        if (existingUser != null) {
            // Actualizar las propiedades del usuario existente con las del usuario actualizado
           
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setDeviceId(updatedUser.getDeviceId());

            // Guardar el usuario actualizado
            User updatedUserEntity = userService.saveUser(existingUser);

            return ResponseEntity.ok(updatedUserEntity);
        } else {
            // El usuario no existe
            return ResponseEntity.notFound().build();
        }
    }

    

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Llamada a la función login del servicio
            User loggedInUser = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
    
            // Puedes devolver el usuario logueado con el código de estado 200 (OK)
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } catch (NotLoginException e) {
            // Manejar una excepción de contraseña incorrecta
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NotFoundException e) {
            // Manejar una excepción de usuario no encontrado o credenciales incorrectas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
