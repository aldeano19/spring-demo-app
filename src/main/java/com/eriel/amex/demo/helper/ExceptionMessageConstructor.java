package com.eriel.amex.demo.helper;

import com.eriel.amex.demo.dto.MapAddress;

public class ExceptionMessageConstructor {
    public static String makeMessageBadAccountNumberFormat(String givenAccountNumber){
        return String.format("The account number < %s > , does not follow the format A123456678 ", givenAccountNumber);
    }

    public static String makeMessageAccountNumberDoesNotExists(String givenAccountNumber){
        return String.format("The account number < %s > , does not exist in the database.", givenAccountNumber);
    }

    public static String makeMessageBadFirstName(String firstName) {
        if(firstName == null){
            return String.format("First Name cannot be null.");
        }

        if(firstName.isEmpty()){
            return String.format("First Name cannot be empty.");
        }

        return String.format("Invalid First Name < %s > . First Name cannot be less than 2 characters.", firstName);
    }

    public static String makeMessageBadLastName(String lastName) {
        if(lastName == null){
            return String.format("Last Name cannot be null.");
        }
        if(lastName.isEmpty()){
            return String.format("Last Name cannot be empty.");
        }
        return String.format("Invalid Last Name < %s > . Last Name cannot be less than 2 characters.", lastName);
    }

    public static String makeMessageBadEyeColor() {
        return String.format("Eye Color cannot be null.");
    }

    public static String makeMessageBadMapAddress(MapAddress mapAddress) {
        if(mapAddress == null){
            return String.format("Address cannot be null.");
        }
        return String.format("Invalid Address: < %s >", mapAddress.toString());
    }
}
