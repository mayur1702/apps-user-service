package com.mayurmistry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "password cannot be empty")
    @Length(min = 8, message = "password should be of minimum 8 characters")
    private String password;

    @NotNull(message = "username cannot be empty")
    private String username;
}
