package com.Alejandro.TFG.Service;

import java.util.List;


import com.Alejandro.TFG.model.Work;
import com.Alejandro.TFG.model.WorkCheckBox;


public interface WorkCheckBoxService {
    WorkCheckBox createWorkCheckBox(WorkCheckBox workCheckBox);
    WorkCheckBox updateWorkCheckBox(WorkCheckBox workCheckBox);
    WorkCheckBox getWorkCheckBoxByID(Long id);
    List<WorkCheckBox> getAllCheckBoxByWork(Work work);
    void deleteLabel(Long WorkCheckBoxId);
}
