package com.eriel.amex.demo.service;

import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.model.User;

import java.util.List;

public interface UserService {

    /**
     * Given a unique user account number, find the minimal user information required.
     * @param userAccount The unique account number
     * @return The user information.
     * @throws AccountDoesNotExistException if the account passed does not exists. */
    MinimalUserInfoDto getMinimalUserInfo(String userAccount) throws AccountDoesNotExistException;

    /**
     * Create a new user.
     * @param createUserDto Information to create the user
     * @return The created entity
     */
    User createUser(CreateUserDto createUserDto);


    /**
     * Return all users
     * @return users with all of their data
     */
    List<User> getAll();
}
