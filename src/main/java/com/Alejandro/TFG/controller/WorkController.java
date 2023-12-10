/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.controller;


import com.Alejandro.TFG.Service.WorkService;
import com.Alejandro.TFG.model.Work;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */

@RestController
@RequestMapping("/api/work/")
public class WorkController {
    @Autowired
    private WorkService workService;

    @GetMapping
    public List<Work> getAllWorks() {
        return workService.getAllWorks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id) {
        Work work = workService.getWorkById(id);
        return work != null ? ResponseEntity.ok(work) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Work> createWork(@RequestBody Work work) {
        Work createdWork = workService.saveWork(work);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWork);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }

   

    
}
