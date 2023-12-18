/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;

import com.Alejandro.TFG.Service.WorkService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.model.Work;
import com.Alejandro.TFG.repository.WorkRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author Alex
 */
@Service
public class WorkServiceImpl implements WorkService{
    
    @Autowired
    private WorkRepository workRepository;

    @Override
    public Work getWorkById(Long id) {
        return workRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Work not found"));
    }

    @Override
    public Work getWorkByTaskId(Long id) {
        return workRepository.findByTaskId(id)
        .orElseThrow(() -> new NotFoundException("Work not found"));
    }

    @Override
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }
   
    @Override
    public Work saveWork(Work work) {
        return workRepository.save(work);
    }

    @Override
    public void deleteWork(Long workId) {
        workRepository.deleteById(workId);
    }

    

   

    
   
    

}
