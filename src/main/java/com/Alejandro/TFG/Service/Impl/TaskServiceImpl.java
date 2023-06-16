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
    @Autowired
    private UserRepository userRepository;
    
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
        task.setType(updatedTask.getType());
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
    

    // Guardar los cambios en la base de datos
    taskRepository.save(task);

    

    }

     @Override
    public List<Task> searchTasksByKeyword(String keyword) {
        return taskRepository.searchByKeyword(keyword);
    }


    @Override
    public List<Task> getAllTaskorderList(User user, String orderBy) {
         List<Task> tasks;
        
        switch (orderBy) {
            case "dueDateAsc":
                tasks = taskRepository.findAllByUserOrderByDueDateAsc(user);
                break;
            case "dueDateDesc":
                tasks = taskRepository.findAllByUserOrderByDueDateDesc(user);
                break;
            case "typeAsc":
                tasks = taskRepository.findAllByUserOrderByTypeAsc(user);
                break;
            case "typeDesc":
                tasks = taskRepository.findAllByUserOrderByTypeDesc(user);
                break;
                
            case "titleAsc":
                tasks = taskRepository.findAllByUserOrderByTitleAsc(user);
                break;
            case "titleDesc":
                tasks = taskRepository.findAllByUserOrderByTitleDesc(user);
                break;
            default:
                tasks = taskRepository.findAllByUserOrderByTitleAsc(user);
                break;
        }

        return tasks;
    }

    
}
