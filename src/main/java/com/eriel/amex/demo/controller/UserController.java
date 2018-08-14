package com.eriel.amex.demo.controller;

import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.service.FileUploadService;
import com.eriel.amex.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Autowired private FileUploadService fileUploadService;

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
        String message = String.format("Creating new user = %s", createUserDto);
        loggingHelper.logLine(message);

        return userService.createUser(createUserDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usersUpload")
    public void bulkUpload(@RequestParam("file") MultipartFile multipartFile){
        String message = "Processing file " + multipartFile.getOriginalFilename();
        loggingHelper.logLine(message);

        fileUploadService.userBulkUpload(multipartFile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<User> findAll(){
        return userService.getAll();
    }
}
