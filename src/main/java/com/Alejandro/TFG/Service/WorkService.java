package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.Work;
import java.util.List;






public interface WorkService {
    List<Work> getAllWorks();
    Work getWorkByTaskId(Long taskId);
    Work saveWork(Work work);
    void deleteWork(Long workId);
    Work getWorkById(Long id);
    
}
