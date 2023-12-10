/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.FirebaseMessagingService;
import com.Alejandro.TFG.Service.TaskService;

import com.Alejandro.TFG.model.Task;


import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/task/")
public class TaskController {
    @Autowired
     private TaskService taskService;

    @Autowired
     private FirebaseMessagingService firebaseMessagingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Task task = taskService.getTaskById(taskId);

        if (task != null) {
            
            // Actualizar las propiedades de la tarea existente con las del objeto actualizado
            task.setType(updatedTask.getType());
            task.setDescription(updatedTask.getDescription());
            task.setUser(updatedTask.getUser());
            task.setTitle(updatedTask.getTitle());

            // Guardar la tarea actualizada
            Task updatedTaskEntity = taskService.saveTask(task);

            return ResponseEntity.ok(updatedTaskEntity);
        } else {
            // La tarea no existe
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByKeyword(@RequestParam("keyword") String keyword) {
        List<Task> tasks = taskService.searchTasksByKeyword(keyword);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getAllTasksByUser(@PathVariable Long userId) {
        return taskService.getAllTasksByUser(userId);
    }

    @PostMapping("/send-notifications")
    public ResponseEntity<String> sendNotifications() {
        String result = firebaseMessagingService.verificarEnvioNotificacionesProgramadas();
        HttpStatus status = result.startsWith("Success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(result);
    }

    
}
