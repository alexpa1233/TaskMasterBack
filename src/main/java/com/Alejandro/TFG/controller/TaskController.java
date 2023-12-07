/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.FirebaseMessagingService;
import com.Alejandro.TFG.Service.TaskService;
import com.Alejandro.TFG.Service.UserService;
import com.Alejandro.TFG.model.NotificationMessage;
import com.Alejandro.TFG.model.Social;
import com.Alejandro.TFG.model.Task;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
     private TaskService taskService;

    @Autowired
     private FirebaseMessagingService firebaseMessagingService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Task existingTask = taskService.getTaskById(taskId);

        if (existingTask != null) {
            // Actualizar las propiedades de la tarea existente con las del objeto actualizado
            existingTask.setType(updatedTask.getType());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setUser(updatedTask.getUser());
            existingTask.setTitle(updatedTask.getTitle());

            // Guardar la tarea actualizada
            Task updatedTaskEntity = taskService.saveTask(existingTask);

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
        return ResponseEntity.ok(task);
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

    @PostMapping("/{taskId}/send-notification")
    public ResponseEntity<String> sendNotification(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);

        if (task != null && task.getType().equals( "SOCIAL")) {
            // Verifica que sea un 'Social' antes de enviar notificación
            if (task.getSocial() instanceof Social) {
               
                return ResponseEntity.ok(firebaseMessagingService.verificarEnvioNotificacionesProgramadas());
            } else {
                return ResponseEntity.badRequest().body("Task does not have a valid Social");
            }
        } else {
            return ResponseEntity.badRequest().body("Task or Social not available");
        }
    }

    
}
