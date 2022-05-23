package com.marcher.services;

import com.marcher.models.User;
import com.marcher.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userDao.findAllByOrderByLastName();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(Integer id){
        User user = this.userDao.findById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> createUser(User user){
        User alreadyExists = this.userDao.findUserByFirstNameAndLastName(user.getFirstName(), user.getLastName()).orElse(null);
        if(!(user.getFirstName().chars().allMatch(Character::isLetter)) || !(user.getLastName().chars().allMatch(Character::isLetter)) || (user.getLastName().length() < 2 || user.getFirstName().length() < 2)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (alreadyExists != null){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(this.userDao.save(user), HttpStatus.OK);
    }

    public void deleteUser(Integer id) {
        this.userDao.deleteById(id);
    }
}
