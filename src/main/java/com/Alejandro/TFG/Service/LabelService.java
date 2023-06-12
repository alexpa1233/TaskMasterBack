package com.Alejandro.TFG.Service;

import java.util.List;

import com.Alejandro.TFG.model.Label;
import com.Alejandro.TFG.model.Task;

public interface LabelService {
    Label createLabel(Label label);
    Label updateLabel(Label label);
    List<Label> getAllLabelByTask(Task task);
    void deleteLabel(Long labelId);
}
