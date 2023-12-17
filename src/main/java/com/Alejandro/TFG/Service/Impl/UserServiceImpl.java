/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;

import com.Alejandro.TFG.Service.UserService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.exception.NotLoginException;
import com.Alejandro.TFG.exception.UnauthorizedException;
import com.Alejandro.TFG.model.User;
import com.Alejandro.TFG.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alex
 */
@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository; 

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(password.equals(user.getPassword())){
                return user;
            }else{
                throw new NotLoginException("Incorrect password");
            }
            
        }

        throw new NotFoundException("User not found or incorrect credentials");
    }
    

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmailAndUsername(String email, String username) {
        return userRepository.findByEmailAndUsername(email, username);
    }
    
}
