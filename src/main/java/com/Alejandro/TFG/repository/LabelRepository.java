package com.Alejandro.TFG.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.Label;
import com.Alejandro.TFG.model.Task;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findAllByTask(Task task);
    
}
