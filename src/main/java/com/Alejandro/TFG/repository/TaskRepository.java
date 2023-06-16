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
    
    List<Task> findByUser(User user);

    @Query("SELECT t FROM Task t JOIN t.user u WHERE u = :user ORDER BY t.dueDate ASC")
    List<Task> findAllOrderByDueDateForUser(@Param("user") User user);

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    List<Task> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.dueDate ASC")
    List<Task> findAllByUserOrderByDueDateAsc(@Param("user") User user); // Ordenar por fecha de vencimiento ascendente

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.dueDate DESC")
    List<Task> findAllByUserOrderByDueDateDesc(@Param("user") User user); // Ordenar por fecha de vencimiento descendente

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.type ASC")
    List<Task> findAllByUserOrderByTypeAsc(@Param("user") User user); // Ordenar por tipo ascendente

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.type DESC")
    List<Task> findAllByUserOrderByTypeDesc(@Param("user") User user); // Ordenar por tipo descendente

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.title ASC")
    List<Task> findAllByUserOrderByTitleAsc(@Param("user") User user); // Ordenar por título ascendente

    @Query("SELECT t FROM Task t WHERE t.user = :user ORDER BY t.title DESC")
    List<Task> findAllByUserOrderByTitleDesc(@Param("user") User user); // Ordenar por título descendente
}
