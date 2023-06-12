package com.Alejandro.TFG.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ProviderCreatingFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Alejandro.TFG.Service.LabelService;
import com.Alejandro.TFG.Service.TaskService;
import com.Alejandro.TFG.model.Label;
import com.Alejandro.TFG.model.Task;

@RestController
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @Autowired
    private TaskService taskService;
    

    @PostMapping
    public ResponseEntity<Label> createLabel(@RequestBody Label label) {
        Label createdLabel = labelService.createLabel(label);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLabel);
    }

    @PutMapping("/{labelId}")
    public ResponseEntity<Label> updateLabel(@PathVariable Long labelId, @RequestBody Label updatedLabel) {
        Label label = labelService.getLabelById(labelId);
        label.setName(updatedLabel.getName());
        // Actualizar otros campos según sea necesario

        labelService.updateLabel(label);
        return ResponseEntity.ok(label);
    }

    @DeleteMapping("/{labelId}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long labelId) {
        labelService.deleteLabel(labelId);
        return ResponseEntity.noContent().build();
    } 

    @GetMapping
    public ResponseEntity<List<Label>> getAllLabels(@RequestParam Long taskId) {
        Task task = taskService.getTaskById(taskId);
        List<Label> labels = labelService.getAllLabelsByTask(task);
        return ResponseEntity.ok(labels);
    }
}
