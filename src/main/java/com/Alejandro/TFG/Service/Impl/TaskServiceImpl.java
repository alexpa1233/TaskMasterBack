/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;


import com.Alejandro.TFG.Service.TaskService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alex
 */
@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
   
    
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
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
    public List<Task> searchTasksByKeyword(String keyword) {
        return taskRepository.searchByKeyword(keyword);
    }

    @Override
    public List<Task> getAllTasksByUser(Long userId) {
        // Implementa la lógica para obtener todas las tareas asociadas a un usuario
        return taskRepository.findByUserId(userId);
    }


   

    
}
