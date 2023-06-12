package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.Task;
import java.util.List;



public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);
    Task getTaskById(Long taskId);
    List<Task> getAllTasks();
    void shareTaskWithUser(Long taskId, Long userId);

    
    
}
