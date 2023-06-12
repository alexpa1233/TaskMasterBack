package com.Alejandro.TFG.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.Alejandro.TFG.Service.LabelService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.Label;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.repository.LabelRepository;

public class LabelServiceImpl implements LabelService{
    @Autowired
    private LabelRepository labelRepository;

    @Override
    public Label createLabel(Label label) {
        return labelRepository.save(label);
    }
    
    @Override
    public Label getLabelByID(Long id) {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        return optionalLabel.orElseThrow(() -> new NotFoundException("Label not found"));
    }

    @Override
    public Label updateLabel(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void deleteLabel(Long labelId) {
        labelRepository.deleteById(labelId);
    }

    @Override
    public List<Label> getAllLabelByTask(Task task) {
        return labelRepository.findAllByTask(task);
    }
    
}
