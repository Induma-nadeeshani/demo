package com.practice.library.service;

import com.practice.library.model.User;
import com.practice.library.repository.UserRepositiry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepositiry userRepositiry;
    private Logger logger;

    public UserService(UserRepositiry userRepositiry) {
        this.userRepositiry = userRepositiry;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    //GetMapping method to get all users
    public List<User> getAllUsers(){
        logger.info("GETTING_ALL_USERS");
        List<User> users = userRepositiry.findAll();

        if (users.isEmpty())
            throw new NoSuchElementException("No users in the database");

        return users;
    }

    //GetMapping method to get user by ID
    public Optional<User> getUserById(Long id){
        logger.info("GETTING_USER_BY_ID {} ", id);
        return userRepositiry.findById(id);
    }

    //PostMapping Method
    public User createUser(User user){
        logger.info("CREATE_USER ", user);
        Optional<User> existingUser = userRepositiry.findUserByRegNo(user.getRegNo());

        if(existingUser.isPresent()) throw new DataIntegrityViolationException("User with this Registration number already exist");
        userRepositiry.save(user);
        return user;
    }

    //PutMapping Method
    public User updateUser(Long id,User user){
        logger.info("UPDATE_USER");
        Optional<User> existingUser = userRepositiry.findById(id);

        if(existingUser.isPresent()){
            User foundUser = existingUser.get();
            foundUser.setRegNo(user.getRegNo());
            foundUser.setName(user.getName());

             userRepositiry.save(foundUser);
             return foundUser;
        }else {
            throw new NoSuchElementException("User not found for this ID");
        }
    }

    //DeleteMapping Method
    public void deleteUser(Long id){
        logger.info("DELETE_USER");
        Optional<User> existingUser = userRepositiry.findById(id);

        if(existingUser.isPresent()){
            userRepositiry.delete(existingUser.get());
        }else {
            throw new NoSuchElementException("User not found for this ID");
        }
    }

}
