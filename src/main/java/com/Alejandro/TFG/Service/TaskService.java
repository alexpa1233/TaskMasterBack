package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.User;

import java.util.List;
import java.util.Optional;



public interface TaskService {
    Task saveTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Long taskId);
    Task getTaskById(Long taskId);
    List<Task> getAllTasks();
    List<Task> searchTasksByKeyword(String keyword);
    List<Task> getAllTasksByUser(Long userId);
    
    
    
}
