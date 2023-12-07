package com.Alejandro.TFG.controller;



import com.Alejandro.TFG.Service.WorkCheckBoxService;
import com.Alejandro.TFG.model.WorkCheckBox;
import com.Alejandro.TFG.model.Work;
import com.Alejandro.TFG.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workCheckBox")
public class WorkCheckBoxController {

    @Autowired
    private WorkCheckBoxService workCheckBoxService;

    @PostMapping("/create")
    public ResponseEntity<WorkCheckBox> createWorkCheckBox(@RequestBody WorkCheckBox workCheckBox) {
        WorkCheckBox createdWorkCheckBox = workCheckBoxService.createWorkCheckBox(workCheckBox);
        return new ResponseEntity<>(createdWorkCheckBox, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<WorkCheckBox> updateWorkCheckBox(@RequestBody WorkCheckBox workCheckBox) {
        WorkCheckBox updatedWorkCheckBox = workCheckBoxService.updateWorkCheckBox(workCheckBox);
        return new ResponseEntity<>(updatedWorkCheckBox, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkCheckBox> getWorkCheckBoxById(@PathVariable Long id) {
        WorkCheckBox workCheckBox = workCheckBoxService.getWorkCheckBoxByID(id);
        return new ResponseEntity<>(workCheckBox, HttpStatus.OK);
    }

    @GetMapping("/getAllByWork/{workId}")
    public ResponseEntity<List<WorkCheckBox>> getAllCheckBoxByWork(@PathVariable Long workId) {
        Work work = new Work();
        work.setId(workId);

        List<WorkCheckBox> checkBoxList = workCheckBoxService.getAllCheckBoxByWork(work);
        return new ResponseEntity<>(checkBoxList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        workCheckBoxService.deleteLabel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

