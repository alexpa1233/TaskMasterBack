package com.Alejandro.TFG.Service;

import com.Alejandro.TFG.model.User;
import java.util.List;

public interface UserService {
    
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    List<User> getAllUsers();
    User login(String email, String password);
}
