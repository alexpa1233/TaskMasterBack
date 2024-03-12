package com.Alejandro.TFG.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.Work;


@Repository
public interface WorkRepository extends JpaRepository<Work,Long>{

    Work findByTaskId(Long taskId);
}
