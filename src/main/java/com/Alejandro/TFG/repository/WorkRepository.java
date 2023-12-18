package com.Alejandro.TFG.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.Work;


@Repository
public interface WorkRepository extends JpaRepository<Work,Long>{
    Optional<Work> findByTaskId(Long taskId);
}
