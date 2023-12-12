package com.Alejandro.TFG.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.Step;


@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findAllByWorkId(Long workId);

    Step findById(long id);
}
