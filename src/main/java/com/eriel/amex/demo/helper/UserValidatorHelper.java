package com.eriel.amex.demo.helper;

import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.MapAddress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorHelper {
    public static int MINIMUM_FIRST_NAME_LENGTH = 1;
    public static int MINIMUM_LAST_NAME_LENGTH = 1;

    public static boolean isValidAccountNumber(String accountNumber){

        // Matches any string that starts with an 'A' and is followed by any amount of digits.
        Pattern accountNumberPattern = Pattern.compile("^A\\d+$");

        Matcher matcher = accountNumberPattern.matcher(accountNumber);
        return matcher.matches();
    }

    /**
     * Check firstName is larger than 1 character.
     * @param firstName
     * @return whether it is a valid firstName
     */
    public static boolean isValidFirstName(String firstName) {
        return firstName != null && !firstName.isEmpty() && firstName.length() > MINIMUM_FIRST_NAME_LENGTH;
    }

    /**
     * Check lastName is larger than 1 character.
     * @param lastName
     * @return whether it is a valid lastName
     */
    public static boolean isValidLastName(String lastName) {
        return lastName != null && !lastName.isEmpty() && lastName.length() > MINIMUM_LAST_NAME_LENGTH;
    }

    /**
     * Check eyeColor is not null
     * @param eyeColor
     * @return whether it is a valid eyeColor
     */
    public static boolean isValidEyeColor(EyeColorEnum eyeColor) {
        return eyeColor != null;
    }

    /**
     * Check all required properties of  MapAddress exist.
     * @param mapAddress
     * @return whether is is a valid MapAddress
     */
    public static boolean isValidMapAddress(MapAddress mapAddress) {
        return !(mapAddress == null ||

                mapAddress.getStreetAddress() == null ||
                mapAddress.getZipcode() == null ||
                mapAddress.getCountry() == null ||
                mapAddress.getCity() == null ||

                mapAddress.getStreetAddress().isEmpty() ||
                mapAddress.getZipcode().isEmpty() ||
                mapAddress.getCountry().isEmpty() ||
                mapAddress.getCity().isEmpty());
    }
}
