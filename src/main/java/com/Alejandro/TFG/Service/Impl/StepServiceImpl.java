package com.Alejandro.TFG.Service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.Alejandro.TFG.Service.StepService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.Step;
import com.Alejandro.TFG.repository.StepRepository;

@Service
public class StepServiceImpl implements StepService{
    @Autowired
    private StepRepository stepRepository;

    @Override
    public Step saveStep(Step step) {
        return stepRepository.save(step);
    }

    @Override
    public Step getStepByID(Long id) {
       return stepRepository.findById(id)
       .orElseThrow(() -> new NotFoundException("Section not found"));
    }

    @Override
    public List<Step> getAllStepByWorkId(Long Id) {
        return stepRepository.findAllByWorkId(Id);
        
    }

    @Override
    public void deleteStep(Long StepId) {
        stepRepository.deleteById(StepId);
    }
    
}
