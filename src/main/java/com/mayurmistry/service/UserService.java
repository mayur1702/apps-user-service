package com.mayurmistry.service;

import com.mayurmistry.Entity.UserEntity;
import com.mayurmistry.Repository.UserRepository;
import com.mayurmistry.exception.CredentialsDoNotMatch;
import com.mayurmistry.exception.UserNotFoundException;
import com.mayurmistry.model.LoginRequest;
import com.mayurmistry.model.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean registerNewUser(RegisterUserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userRepository.save(userEntity);
        return true;
    }

    public UserEntity loginUser(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername());
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        if (!loginRequest.getPassword().equals(userEntity.getPassword())) {
            throw new CredentialsDoNotMatch();
        }
        return userEntity;

    }
}
