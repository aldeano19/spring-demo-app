package com.eriel.amex.demo.helper;

import com.eriel.amex.demo.dto.CreateUserDto;
import org.bson.types.ObjectId;

public class AccountNumberGenerator {
    final static String USER_ACCOUNT_PREFIX = "A";


    /**
     * Generates a random account umber for this user. Prefixes the letter 'A' to a sequence of digits.
     * A better implementation would include checking if the generated account number already exists in the database.
     *
     * @param createUserDto the information for the user to be created encapsulated in a POJO
     * @return The generated account number
     */
    public static String generateAccountNumber(CreateUserDto createUserDto){
        return USER_ACCOUNT_PREFIX + Math.abs(createUserDto.hashCode());
    }

    public static String generateAccountNumber(ObjectId objectId){
        return USER_ACCOUNT_PREFIX + Math.abs(objectId.hashCode());
    }
}
