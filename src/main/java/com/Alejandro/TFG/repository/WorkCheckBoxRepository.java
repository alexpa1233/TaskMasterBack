package com.Alejandro.TFG.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.WorkCheckBox;
import com.Alejandro.TFG.model.Work;

@Repository
public interface WorkCheckBoxRepository extends JpaRepository<WorkCheckBox, Long> {
    List<WorkCheckBox> findAllByWork(Work work);

    WorkCheckBox findById(long id);
}
