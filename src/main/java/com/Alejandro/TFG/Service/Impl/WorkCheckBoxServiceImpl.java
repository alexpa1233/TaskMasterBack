package com.Alejandro.TFG.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alejandro.TFG.Service.WorkCheckBoxService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.WorkCheckBox;
import com.Alejandro.TFG.repository.WorkCheckBoxRepository;
import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.Work;

@Service
public class WorkCheckBoxServiceImpl implements WorkCheckBoxService{
    @Autowired
    private WorkCheckBoxRepository workCheckBoxRepository;

    @Override
    public WorkCheckBox createWorkCheckBox(WorkCheckBox workCheckBox) {
        
        return workCheckBoxRepository.save(workCheckBox);
    }

    @Override
    public WorkCheckBox updateWorkCheckBox(WorkCheckBox workCheckBox) {
       return workCheckBoxRepository.save(workCheckBox);
    }

    @Override
    public WorkCheckBox getWorkCheckBoxByID(Long id) {
       return workCheckBoxRepository.findById(id)
       .orElseThrow(() -> new NotFoundException("Section not found"));
    }

    @Override
    public List<WorkCheckBox> getAllCheckBoxByWork(Work work) {
        return workCheckBoxRepository.findAllByWork(work);
        
    }

    @Override
    public void deleteLabel(Long WorkCheckBoxId) {
        workCheckBoxRepository.deleteById(WorkCheckBoxId);
    }
    
}
