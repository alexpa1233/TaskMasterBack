package com.Alejandro.TFG.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Alejandro.TFG.model.Social;



public interface SocialRepository extends JpaRepository<Social, Long>{
    Optional<Social> findByTaskId(Long taskId);
    
}
