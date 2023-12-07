package com.Alejandro.TFG.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.Work;
import com.Alejandro.TFG.model.Task;

@Repository
public interface WorkRepository extends JpaRepository<Work,Long>{

}
