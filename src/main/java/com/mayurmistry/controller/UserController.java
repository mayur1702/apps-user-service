package com.mayurmistry.controller;

import com.mayurmistry.Entity.UserEntity;
import com.mayurmistry.Repository.UserRepository;
import com.mayurmistry.model.UserRequest;
import com.mayurmistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public boolean registerUser(@RequestBody @Valid UserRequest userRequest) {
        userService.registerNewUser(userRequest);
        return true;
    }

}
