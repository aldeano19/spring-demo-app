package com.eriel.amex.demo.service.impl;

import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.PostalAddress;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.exceptions.InvalidInputObjectData;
import com.eriel.amex.demo.helper.AccountNumberGenerator;
import com.eriel.amex.demo.helper.ExceptionMessageConstructor;
import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.helper.UserValidatorHelper;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.repository.UserRepository;
import com.eriel.amex.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private LoggingHelper loggingHelper = LoggingHelper.getInstance();

    /**
     * Provides direct access to user info in database.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * @inheritDoc
     */
    @Override
    public MinimalUserInfoDto getMinimalUserInfo(String userAccount) throws AccountDoesNotExistException {

        String message = "Validating input.";
        loggingHelper.logLine(message);

        // Validate the format of the userAccount -> Throw exception if invalid format
        if(!UserValidatorHelper.isValidAccountNumber(userAccount)) {
            String errorMessage = ExceptionMessageConstructor.makeMessageBadAccountNumberFormat(userAccount);

            loggingHelper.logLine(errorMessage);

            throw new InvalidInputObjectData(errorMessage);
        }

        // If result is null, there is no account with that number. Throw exception and show 404 to user.
        MinimalUserInfoDto minimalUserInfoDto = userRepository.getMinimalUserInfo(userAccount);
        if(minimalUserInfoDto == null){

            String errorMessage = String.format("Account = %s doesn't exists", userAccount);
            loggingHelper.logLine(errorMessage);

            throw new AccountDoesNotExistException(userAccount);
        }

        return minimalUserInfoDto;
    }

    /**
     * @inheritDoc
     */
    @Override
    public User createUser(CreateUserDto createUserDto) {
        String firstName = createUserDto.getFirstName();
        String lastName  = createUserDto.getLastName();
        String email = createUserDto.getEmail();
        EyeColorEnum eyeColor = createUserDto.getEyeColor();
        PostalAddress postalAddress = createUserDto.getAddress();

        String message = "Validating input.";
        loggingHelper.logLine(message);
        // Validate the data for the user
        // TODO: Missing validation for email.
        if(!UserValidatorHelper.isValidFirstName(firstName)){
            String errorMessage = ExceptionMessageConstructor.makeMessageBadFirstName(firstName);
            loggingHelper.logLine(errorMessage);
            throw new InvalidInputObjectData(errorMessage);
        }

        if(!UserValidatorHelper.isValidLastName(lastName)){
            String errorMessage = ExceptionMessageConstructor.makeMessageBadLastName(lastName);
            loggingHelper.logLine(errorMessage);
            throw new InvalidInputObjectData(errorMessage);
        }

        if(!UserValidatorHelper.isValidEyeColor(eyeColor)){
            String errorMessage = ExceptionMessageConstructor.makeMessageBadEyeColor();
            loggingHelper.logLine(errorMessage);
            throw new InvalidInputObjectData(errorMessage);
        }

        if(!UserValidatorHelper.isValidMapAddress(postalAddress)){
            String errorMessage = ExceptionMessageConstructor.makeMessageBadMapAddress(postalAddress);
            loggingHelper.logLine(errorMessage);
            throw new InvalidInputObjectData(errorMessage);
        }

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setAddress(postalAddress);
        newUser.setEyeColor(eyeColor);
        newUser.setAccountNumber(AccountNumberGenerator.generateAccountNumber(createUserDto));

        newUser = userRepository.save(newUser);


        message = String.format("Inserted new user with id=%s", newUser.getId().toString());
        loggingHelper.logLine(message);
        // TODO: Check the user was created successfully. If not, throw exception.

        return newUser;
    }

    @Override
    public List<User> getAll() {
        String message = "Getting all users.";
        loggingHelper.logLine(message);
        return userRepository.findAll();
    }
}
