package com.Alejandro.TFG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.User;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByUsurnameAndPassword(String usurname, String password);

    Optional<User> findById(Long id);

    Optional<User> findByDeviceID(String deviceId);
    
    List<User> findByEmailIn(List<String> emails);

}
