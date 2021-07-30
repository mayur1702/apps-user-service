package com.mayurmistry.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Getter
@Setter
public class CustomBindingException extends RuntimeException{

    private final BindingResult bindingResult;

    public CustomBindingException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
