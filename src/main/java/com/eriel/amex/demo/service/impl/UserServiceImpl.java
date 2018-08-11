package com.eriel.amex.demo.service.impl;

import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MapAddress;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.helper.AccountNumberGenerator;
import com.eriel.amex.demo.helper.ExceptionMessageConstructor;
import com.eriel.amex.demo.helper.UserValidatorHelper;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.repository.UserRepository;
import com.eriel.amex.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

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

        // Validate the format of the userAccount -> Throw exception if invalid format
        if(!UserValidatorHelper.isValidAccountNumber(userAccount)) {
            String errorMessage = ExceptionMessageConstructor.makeMessageBadAccountNumberFormat(userAccount);
            throw new IllegalArgumentException(errorMessage);
        }

        // If result is null, there is no account with that number. Throw exception and show 404 to user.
        MinimalUserInfoDto minimalUserInfoDto = userRepository.getMinimalUserInfo(userAccount);
        if(minimalUserInfoDto == null){
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
        EyeColorEnum eyeColor = createUserDto.getEyeColor();
        MapAddress mapAddress = createUserDto.getAddress();

        // Validate the data for the user
        if(!UserValidatorHelper.isValidFirstName(firstName)){
            throw new IllegalArgumentException(ExceptionMessageConstructor.makeMessageBadFirstName(firstName));
        }

        if(!UserValidatorHelper.isValidLastName(lastName)){
            throw new IllegalArgumentException(ExceptionMessageConstructor.makeMessageBadLastName(lastName));
        }

        if(!UserValidatorHelper.isValidEyeColor(eyeColor)){
            throw new IllegalArgumentException(ExceptionMessageConstructor.makeMessageBadEyeColor());
        }

        if(!UserValidatorHelper.isValidMapAddress(mapAddress)){
            throw new IllegalArgumentException(ExceptionMessageConstructor.makeMessageBadMapAddress(mapAddress));
        }

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setAddress(mapAddress);
        newUser.setEyeColor(eyeColor);
        newUser.setAccountNumber(AccountNumberGenerator.generateAccountNumber(createUserDto));

        newUser = userRepository.save(newUser);

        // TODO: Check the user was created successfully. If not, throw exception.

        return newUser;
    }
}
