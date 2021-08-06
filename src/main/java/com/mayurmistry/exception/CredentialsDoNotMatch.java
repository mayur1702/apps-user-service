package com.mayurmistry.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CredentialsDoNotMatch extends RuntimeException{
}
