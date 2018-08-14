package com.eriel.amex.demo.controller;

import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.service.FileUploadService;
import com.eriel.amex.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Defines client facing operations to be performed on Users.
 */

@RestController
@RequestMapping("/users")
@Api(value = "usersaccess",description = "Operations to interact with users")
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
    @ApiOperation(value = "Get some info about the user with this account", response = MinimalUserInfoDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved User info"),
            @ApiResponse(code = 404, message = "Account not fount"),
            @ApiResponse(code = 422, message = "Bad User lookup info")
    })
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
    @ApiOperation(value = "Create a new User", response = User.class)
    public User create(CreateUserDto createUserDto){
        String message = String.format("Creating new user = %s", createUserDto);
        loggingHelper.logLine(message);

        return userService.createUser(createUserDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usersUpload")
    @ApiOperation(value = "Upload a zip file with spreadsheets where every row is a user")
    public void bulkUpload(@RequestParam("file") MultipartFile multipartFile){
        String message = "Processing file " + multipartFile.getOriginalFilename();
        loggingHelper.logLine(message);

        fileUploadService.userBulkUpload(multipartFile);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ApiOperation(value = "Get all existing users. No pagination implemented", response = List.class)
    public List<User> findAll(){
        return userService.getAll();
    }
}
