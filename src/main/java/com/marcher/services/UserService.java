package com.marcher.services;

import com.marcher.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> getUserById(Integer id);
    ResponseEntity<User> createUser(User user);
    void deleteUser(Integer id);

}
