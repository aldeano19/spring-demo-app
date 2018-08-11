package com.eriel.amex.demo.helper;

import com.eriel.amex.demo.builder.CreateUserDtoBuilder;
import com.eriel.amex.demo.dto.CreateUserDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountNumberGeneratorTest {

    @Test
    public void generateAccountNumber_buildsValidAccountNumber() {
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder().build();
        String fakeAccountNumber = AccountNumberGenerator.generateAccountNumber(fakeCreateUserDto);
        
        assertTrue(UserValidatorHelper.isValidAccountNumber(fakeAccountNumber));
    }
}