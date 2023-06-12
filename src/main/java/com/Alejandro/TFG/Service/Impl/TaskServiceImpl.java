/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;

import com.Alejandro.TFG.Service.NotificationService;
import com.Alejandro.TFG.Service.TaskService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.User;
import com.Alejandro.TFG.repository.TaskRepository;
import com.Alejandro.TFG.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Alex
 */
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private NotificationService notificationService;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepo, NotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepo;
        this.notificationService = notificationService;
    }
    

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
    
   @Override
    public Task updateTask(Long taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        // Actualizar los atributos de la tarea existente con los valores del objeto updatedTask
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());

        // Guardar los cambios en la base de datos
        taskRepository.save(task);

        return task;
    }
    

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task getTaskById(Long taskId) {
         return taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void shareTaskWithUser(Long taskId, Long userId) {
        Task task = getTaskById(taskId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        
        // Verificar si la tarea ya está asignada a otro usuario
    if (task.getUser() != null && !task.getUser().equals(user)) {
        throw new IllegalArgumentException("Task is already assigned to another user");
    }

    // Asignar la tarea al usuario
    task.setUser(user);
    task.setUpdatedAt(LocalDateTime.now());

    // Guardar los cambios en la base de datos
    taskRepository.save(task);

    

    }

    
}
