package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    List<User> getAllUsers();
    Optional<User> findByEmailAndUsername(String email, String username);
    User login(String username, String password);
    List<User> findAll();
}
