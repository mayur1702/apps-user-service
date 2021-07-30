package com.mayurmistry.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String username;

    private String phoneNumber;

}
