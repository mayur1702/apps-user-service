package com.mayurmistry.controller;

import com.mayurmistry.Entity.UserEntity;
import com.mayurmistry.exception.CustomBindingException;
import com.mayurmistry.model.LoginRequest;
import com.mayurmistry.model.RegisterUserRequest;
import com.mayurmistry.model.StandardResponse;
import com.mayurmistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public StandardResponse registerUser(@RequestBody @Valid RegisterUserRequest userRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new CustomBindingException(result);
        }
        userService.registerNewUser(userRequest);
        return new StandardResponse("Registered successfully");
    }

    @PostMapping(value = "/login")
    public StandardResponse loginUser(@RequestBody @Valid LoginRequest loginRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new CustomBindingException(result);
        }
        UserEntity userEntity = userService.loginUser(loginRequest);
        return new StandardResponse("Logged in successfully as " + userEntity.getUsername());
    }

}
