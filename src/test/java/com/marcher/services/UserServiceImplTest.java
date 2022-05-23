package com.marcher.services;

import com.marcher.models.User;
import com.marcher.repository.UserDao;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    UserServiceImpl userServiceImpl;

    @Mock
    UserDao userDao;

    @BeforeEach
    void setUp(){
        userServiceImpl = new UserServiceImpl(userDao);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "michael", "archer"));
        ResponseEntity<List<User>> expectedResult = new ResponseEntity<>(users, HttpStatus.OK);
        Mockito.when(userDao.findAllByOrderByLastName()).thenReturn(users);

        ResponseEntity<List<User>> actualResult = userServiceImpl.getAllUsers();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getUserById() {
        Integer id = 1;
        User user = new User(1, "michael", "archer");
        ResponseEntity<User> expectedResult = new ResponseEntity<>(user, HttpStatus.OK);
        Mockito.when(userDao.findById(id)).thenReturn(Optional.of(user));

        ResponseEntity<User> actualResult = userServiceImpl.getUserById(id);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getUserByIdReturnNull() {
        Integer id = 1;
        User user = null;
        ResponseEntity<User> expectedResult = new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        Mockito.when(userDao.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<User> actualResult = userServiceImpl.getUserById(id);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createUser(){
        User user = new User(1, "Michael", "Archer");
        ResponseEntity<User> expectedResult = new ResponseEntity<>(user, HttpStatus.CREATED);
        Mockito.when(userDao.save(user)).thenReturn(user);

        ResponseEntity<User> actualResult = userServiceImpl.createUser(user);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createUserWhenFullNameAlreadyExists() {
        ResponseEntity<User> expectedResult = new ResponseEntity<>(null, HttpStatus.CONFLICT);
        User user = new User(1, "Michael", "Archer");
        Mockito.when(userDao.findUserByFirstNameAndLastName(user.getFirstName(), user.getLastName())).thenReturn(Optional.of(user));

        ResponseEntity<User> actualResult = userServiceImpl.createUser(user);

        assertEquals(expectedResult, actualResult);
        Mockito.verify(userDao, Mockito.times(0)).save(user);
    }

    @Test
    void deleteUser() {
        Integer id = 1;
        Mockito.doNothing().when(userDao).deleteById(id);

        userServiceImpl.deleteUser(id);

        Mockito.verify(userDao, Mockito.times(1)).deleteById(1);
    }
}