package com.eriel.amex.demo.helper;

import com.eriel.amex.demo.builder.MapAddressBuilder;
import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.MapAddress;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserValidatorHelperTest {

    @Test
    public void isValidAccountNumber_withValidAccountNumber_returnTrue() {
        String accountNumber = "A12334556790";
        assertTrue(UserValidatorHelper.isValidAccountNumber(accountNumber));
    }

    @Test
    public void isValidAccountNumber_withAccountMissingPrefix_returnFalse() {
        String accountNumber = "12334556790";
        assertFalse(UserValidatorHelper.isValidAccountNumber(accountNumber));
    }

    @Test
    public void isValidAccountNumber_withMoreThanOneLetter_returnFalse() {
        String accountNumber = "ASD12334556790";
        assertFalse(UserValidatorHelper.isValidAccountNumber(accountNumber));
    }

    @Test
    public void isValidFirstName_withValidFirstName_returnTrue() {
        String firstName = "Jack";
        assertTrue(UserValidatorHelper.isValidFirstName(firstName));
    }

    @Test
    public void isValidFirstName_withNullFirstName_returnFalse() {
        String firstName = null;
        assertFalse(UserValidatorHelper.isValidFirstName(firstName));
    }

    @Test
    public void isValidFirstName_withShortFirstName_returnFalse() {
        String firstName = "J";
        assertFalse(UserValidatorHelper.isValidFirstName(firstName));
    }

    @Test
    public void isValidLastName_withValidLastName_returnTrue() {
        String lastName = "Newton";
        assertTrue(UserValidatorHelper.isValidFirstName(lastName));
    }

    @Test
    public void isValidFirstName_withNullLastName_returnFalse() {
        String lastName = null;
        assertFalse(UserValidatorHelper.isValidLastName(lastName));
    }

    @Test
    public void isValidLastName_withShortLastName_returnFalse() {
        String firstName = "N";
        assertFalse(UserValidatorHelper.isValidFirstName(firstName));
    }

    @Test
    public void isValidEyeColor_withNullEyeColor_returnFalse() {
        EyeColorEnum eyeColorEnum = null;
        assertFalse(UserValidatorHelper.isValidEyeColor(eyeColorEnum));
    }

    @Test
    public void isValidMapAddress_withBadMapAddress_returnFalse() {
        MapAddress fakeMapAddress = new MapAddressBuilder().withCity(null).build();

        assertFalse(UserValidatorHelper.isValidMapAddress(fakeMapAddress));
    }
}