package com.Alejandro.TFG.Service;

import java.util.List;
import java.util.Optional;

import com.Alejandro.TFG.model.Label;
import com.Alejandro.TFG.model.Task;

public interface LabelService {
    Label createLabel(Label label);
    Label updateLabel(Label label);
    Label getLabelByID(Long id);
    List<Label> getAllLabelByTask(Task task);
    void deleteLabel(Long labelId);
}
