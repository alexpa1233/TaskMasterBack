package com.Alejandro.TFG.Service;

import java.util.List;


import com.Alejandro.TFG.model.Work;
import com.Alejandro.TFG.model.Step;


public interface StepService {
    Step saveStep(Step step);
    Step getStepByID(Long id);
    List<Step> getAllStepByWorkId(Long workId);
    void deleteStep(Long stepId);
}
