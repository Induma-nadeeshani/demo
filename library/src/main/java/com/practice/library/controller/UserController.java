package com.practice.library.controller;

import com.practice.library.dto.ResponseHandler;
import com.practice.library.model.User;
import com.practice.library.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    private Logger logger;

    public UserController(UserService userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getAllUsers(){
        logger.info("USER: " + "GET_ALL_USERS");

        try {
           List<User> users = userService.getAllUsers();
           return ResponseHandler.generateResponse("Users Recieved Successfully", HttpStatus.OK, users);
        }catch (NoSuchElementException e){
            logger.error("RETRIEVAL_FAILED", e.getMessage());
            return ResponseHandler.generateResponse("RETRIEVAL_FAILED", HttpStatus.NOT_FOUND,null);
        }catch (Exception ex){
            logger.error("RETRIEVAL_FAILED", ex.getMessage());
            return ResponseHandler.generateResponse("RETRIEVAL_FAILED", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

//    @PostMapping("book")
//    public ResponseEntity<Object> createUser(UserRequest userRequest){
//
//    }
}
