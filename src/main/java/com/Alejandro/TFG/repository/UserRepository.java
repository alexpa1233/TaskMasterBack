package com.Alejandro.TFG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Alejandro.TFG.model.User;
import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndUsername(String email, String username);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByDeviceId(String deviceId);
    
    List<User> findByEmailIn(List<String> emails);

}
