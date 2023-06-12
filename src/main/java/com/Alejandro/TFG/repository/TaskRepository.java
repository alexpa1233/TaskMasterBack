package com.Alejandro.TFG.repository;

import com.Alejandro.TFG.model.Task;
import com.Alejandro.TFG.model.User;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    @Query(value = "SELECT t.* FROM Task t JOIN User u ON t.user_id = u.id where t.user_id = ?1")
    List<Task> findByUser(Long userId);

    @Query("SELECT t.* FROM Task t JOIN User u ON t.user_id = u.id WHERE t.user.id = ?1 ORDER BY t.due_date ASC")
    List<Task> findAllOrderByDueDateForUser(Long userId);

    @Query("SELECT * FROM Task WHERE title LIKE %?1% OR description LIKE %?1%")
    List<Task> searchByKeyword(String keyboard);

    List<Task> findAllByUserOrderByDueDateAsc(User user); // Ordenar por fecha de vencimiento ascendente

    List<Task> findAllByUserOrderByDueDateDesc(User user); // Ordenar por fecha de vencimiento descendente

    List<Task> findAllByUserOrderByTypeAsc(User user); // Ordenar por tipo ascendente

    List<Task> findAllByUserOrderByTypeDesc(User user); // Ordenar por tipo descendente

    List<Task> findAllByUserOrderByTitle(User user);//Ordenar por titulo



}
