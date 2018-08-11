package com.eriel.amex.demo.exceptions;

import com.eriel.amex.demo.helper.ExceptionMessageConstructor;

public class AccountDoesNotExistException extends Exception {

    public AccountDoesNotExistException(String accountNumber){
        super(ExceptionMessageConstructor.makeMessageAccountNumberDoesNotExists(accountNumber));
    }
}
