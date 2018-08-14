package com.eriel.amex.demo.controller;

import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Defines client facing operations to be performed on Users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private LoggingHelper loggingHelper = LoggingHelper.getInstance();

    /**
     * User service Bean to interact with user data.
     */
    @Autowired private UserService userService;

    /**
     * Get a users first name, last name and address.
     * @param userAccountNumber The account number that uniquely identifies this user account
     * @return Some info about this user.
     */
    @RequestMapping(value = "/info/{userAccountNumber}", method = RequestMethod.GET)
    public MinimalUserInfoDto getMinimalUserInfo(@PathVariable String userAccountNumber) throws AccountDoesNotExistException {
        String message = String.format("Getting User by account number = %s", userAccountNumber);
        loggingHelper.logLine(message);

        return userService.getMinimalUserInfo(userAccountNumber);
    }

    /**
     * Create a new User
     * @param createUserDto Required information to create the User
     * @return The successfully created User in the database.
     */
    @RequestMapping(method = RequestMethod.POST)
    public User create(CreateUserDto createUserDto){
        return userService.createUser(createUserDto);
    }
}
