package com.Alejandro.TFG.controller;



import com.Alejandro.TFG.Service.StepService;
import com.Alejandro.TFG.model.Social;
import com.Alejandro.TFG.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/step/")
public class StepController {

    @Autowired
    private StepService stepService;

    @PostMapping
    public ResponseEntity<Step> createStep(@RequestBody Step step) {
        Step createdStep = stepService.saveStep(step);
        return new ResponseEntity<>(createdStep, HttpStatus.CREATED);
    }

    @PutMapping("/{stepId}")
    public ResponseEntity<Step> updateStep(@RequestBody Step workCheckBox,  @PathVariable Long stepId) {
        Step workCheckBoxold = stepService.getStepByID(stepId);
        if (workCheckBox == null) {
            return ResponseEntity.notFound().build();
        }
        workCheckBoxold.setName(workCheckBox.getName());
        Step updatedWorkCheckBox = stepService.saveStep(workCheckBoxold);
        return ResponseEntity.ok(updatedWorkCheckBox);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Step> getStepById(@PathVariable Long id) {
        Step step = stepService.getStepByID(id);
        return new ResponseEntity<>(step, HttpStatus.OK);
    }

    @GetMapping("/getAllByWork/{workId}")
    public List<Step> getAllStepByWorkId(@PathVariable Long workId) {
       return stepService.getAllStepByWorkId(workId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStep(@PathVariable Long id) {
        stepService.deleteStep(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

