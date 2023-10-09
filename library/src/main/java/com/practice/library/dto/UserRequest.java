package com.practice.library.dto;

import com.practice.library.model.User;
import lombok.Data;

@Data
public class UserRequest {

    private String regNo;
    private String name;

    public User toUser(UserRequest userRequest){
        User user = new User();
        user.setRegNo(userRequest.getRegNo());
        user.setName(userRequest.getName());
        return user;
    }
}
