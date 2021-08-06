package com.mayurmistry.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterUserRequest {

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email address provided")
    private String email;

    @NotNull(message = "password cannot be empty")
    @Length(min = 8, max = 16, message = "Password should be between 8 to 16 characters")
    private String password;

    @NotNull(message = "Username cannot be empty")
    @Length(min = 3, message = "Username should contain atleast 3 characters")
    private String username;

    @Nullable
    @Length(min = 10, max = 10, message = "Phone number should have 10 digits")
    private String phoneNumber;

}
