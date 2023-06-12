package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.User;

import java.util.List;



public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);
    Task getTaskById(Long taskId);
    List<Task> getAllTasks();
    List<Task> getAllTaskorderList(User user, String orderBy);
    void shareTaskWithUser(Long taskId, Long userId);
    List<Task> searchTasksByKeyword(String keyword);
    
    
    
}
