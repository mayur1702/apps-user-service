package com.mayurmistry.service;

import com.mayurmistry.Entity.UserEntity;
import com.mayurmistry.Repository.UserRepository;
import com.mayurmistry.model.UserRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean registerNewUser(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userRepository.save(userEntity);
        return true;
    }
}
