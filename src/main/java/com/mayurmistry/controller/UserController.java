package com.mayurmistry.controller;

import com.mayurmistry.exception.CustomBindingException;
import com.mayurmistry.model.LoginRequest;
import com.mayurmistry.model.RegisterUserRequest;
import com.mayurmistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public boolean registerUser(@RequestBody @Valid RegisterUserRequest userRequest) {
        userService.registerNewUser(userRequest);
        return true;
    }

    @PostMapping(value = "/login")
    public boolean loginUser(@RequestBody @Valid LoginRequest loginRequest, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("error occurred");
            throw new CustomBindingException(result);
        }
        return userService.loginUser(loginRequest);
    }

}
