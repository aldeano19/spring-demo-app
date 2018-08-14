package com.eriel.amex.demo.exceptions;

import com.eriel.amex.demo.helper.ExceptionMessageConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // 404
public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(String accountNumber){
        super(ExceptionMessageConstructor.makeMessageAccountNumberDoesNotExists(accountNumber));
    }
}

