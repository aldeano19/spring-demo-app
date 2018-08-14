package com.eriel.amex.demo.controller;

import com.eriel.amex.demo.builder.PostalAddressBuilder;
import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.PostalAddress;

import com.eriel.amex.demo.helper.AccountNumberGenerator;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping("/setup")
public class CheatDatabaseStateController {

    /**
     * No need to go through the Service layer for this operations since this controller is only for demo purposes and
     * highly dangerous in production environments.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * This default setup will create 6 users with well formatted data;
     * @return A string message narrating what was accomplished.
     */
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public List<User> setupDefaultEnvironment(){
        final int TOTAL_TEST_USERS = 6;

        User[] dummyUsers = new User[TOTAL_TEST_USERS];

        for(int i = 0; i < 6; i++){

            ObjectId id = new ObjectId();
            String accountNumber = AccountNumberGenerator.generateAccountNumber(id);
            String firstName = String.format("Lorem%s", i);
            String lastName = String.format("Ipsum%s", i);
            String email = String.format("eriel+%s@testcasecentral.com", i);
            PostalAddress postalAddress = new PostalAddressBuilder().build();
            EyeColorEnum eyeColor = EyeColorEnum.getByVal(i%5);

            dummyUsers[i] = new User(id, accountNumber, email, firstName, lastName, postalAddress, eyeColor);
        }

        List<User> createdUsers = userRepository.saveAll(Arrays.asList(dummyUsers));

        return createdUsers;

    }
}
