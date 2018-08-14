package com.eriel.amex.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY) // 422: Server understands, but can't use this data.
public class InvalidInputObjectData extends IllegalArgumentException {
    public InvalidInputObjectData(String s) {
        super(s);
    }
}
