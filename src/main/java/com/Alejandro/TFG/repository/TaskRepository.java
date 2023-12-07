package com.Alejandro.TFG.repository;

import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.User;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByUserId(Long userId);

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    List<Task> searchByKeyword(@Param("keyword") String keyword);

    //Posibles mejoras ordenar por title, se puede hacer en la APP principal, pero a su vez tambien se puede aquí
}
