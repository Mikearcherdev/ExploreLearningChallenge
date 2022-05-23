package com.marcher.controller;

import com.marcher.models.User;
import com.marcher.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    UserController userController;

    @Mock
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setup(){
        this.userController = new UserController(userServiceImpl);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Michael", "Archer"));
        ResponseEntity<List<User>> expectedResult = new ResponseEntity<>(users, HttpStatus.OK);
        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(expectedResult);

        ResponseEntity<List<User>> actualResult = userController.getAllUsers();

        assertEquals(expectedResult, actualResult);
        Mockito.verify(userServiceImpl, Mockito.times(1)).getAllUsers();
    }

    @Test
    void createUser() {
        User user = new User(1, "Michael", "Archer");
        ResponseEntity<User> expectedResult = new ResponseEntity<>(user, HttpStatus.CREATED);
        Mockito.when(userServiceImpl.createUser(user)).thenReturn(expectedResult);

        ResponseEntity<User> actualResult = userController.createUser(user);

        assertEquals(expectedResult, actualResult);
        Mockito.verify(userServiceImpl, Mockito.times(1)).createUser(user);
    }

    @Test
    void getUserById() {
        User user = new User(1, "Michael", "Archer");
        ResponseEntity<User> expectedResult = new ResponseEntity<>(user, HttpStatus.OK);
        Mockito.when(userServiceImpl.getUserById(user.getId())).thenReturn(expectedResult);

        ResponseEntity<User> actualResult = userController.getUserById(user.getId());

        assertEquals(expectedResult, actualResult);
        Mockito.verify(userServiceImpl, Mockito.times(1)).getUserById(user.getId());
    }

    @Test
    void deleteUser() {
        Integer id = 1;
        Mockito.doNothing().when(userServiceImpl).deleteUser(id);

        userController.deleteUserById(id);

        Mockito.verify(userServiceImpl, Mockito.times(1)).deleteUser(id);
    }
}