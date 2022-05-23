package com.marcher.controller;

import com.marcher.models.User;
import com.marcher.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userController")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("user")
    public ResponseEntity<List<User>> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return this.userService.getUserById(id);
    }

    @DeleteMapping("user/{id}")
    public void deleteUserById(@PathVariable Integer id){
        this.userService.deleteUser(id);
    }
}
