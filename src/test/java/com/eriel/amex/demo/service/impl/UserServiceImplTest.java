package com.eriel.amex.demo.service.impl;

import com.eriel.amex.demo.DemoApplication;
import com.eriel.amex.demo.builder.CreateUserDtoBuilder;
import com.eriel.amex.demo.builder.MinimalUserInfoDtoBuilder;
import com.eriel.amex.demo.builder.UserBuilder;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.exceptions.AccountDoesNotExistException;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class UserServiceImplTest {

    @Mock
    UserRepository mockUserRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMinimalUserInfo_whenAccountNumberIsValid_noException() throws AccountDoesNotExistException {

        final MinimalUserInfoDto fakeMinimalUserInfoDto = new MinimalUserInfoDtoBuilder().build();

        when(mockUserRepository.getMinimalUserInfo(Mockito.any(String.class))).thenReturn(fakeMinimalUserInfoDto);

        String validAccountNumber = "A1234567890";

        MinimalUserInfoDto fakeOutput = userService.getMinimalUserInfo(validAccountNumber);

        assertEquals(fakeMinimalUserInfoDto.getFirstName(), fakeOutput.getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getMinimalUserInfo_whenAccountNumberIsMissingPrefix_thenException() throws AccountDoesNotExistException {

        final MinimalUserInfoDto fakeMinimalUserInfoDto = new MinimalUserInfoDtoBuilder().build();

        when(mockUserRepository.getMinimalUserInfo(Mockito.any(String.class))).thenReturn(fakeMinimalUserInfoDto);

        String missingPrefixAccountNumber = "1234567890";

        userService.getMinimalUserInfo(missingPrefixAccountNumber); // Should throw an exception here.
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void getMinimalUserInfo_whenNullResultForAccountNumber_thenAccountDoesNotExistException() throws AccountDoesNotExistException {

        when(mockUserRepository.getMinimalUserInfo(Mockito.any(String.class))).thenReturn(null);

        String validAccountNumber = "A1234567890";

        // Will throw exception here since the response from the repository was null
        userService.getMinimalUserInfo(validAccountNumber);
    }

    @Test
    public void createUser_whenValidNewUserData_noException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder().build();
        final User fakeNewUser = new UserBuilder().buildFromCreateUserDto(fakeCreateUserDto);

        when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(fakeNewUser);

        User newlyCreatedUser = userService.createUser(fakeCreateUserDto);

        assertEquals(fakeNewUser.getId().toString(), newlyCreatedUser.getId().toHexString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenFirstNameIsNull_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withFirstName(null).build();

        userService.createUser(fakeCreateUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenFirstNameIsEmpty_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withFirstName("").build();

        userService.createUser(fakeCreateUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenFirstNameSizeIsLessThanTwo_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withFirstName("H").build();

        userService.createUser(fakeCreateUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenLastNameIsNull_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withLastName(null).build();

        userService.createUser(fakeCreateUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenLastNameIsEmpty_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withLastName("").build();

        userService.createUser(fakeCreateUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenLastNameSizeIsLessThanTwo_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withLastName("H").build();

        userService.createUser(fakeCreateUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_whenEyeColorIsNull_thenException(){
        final CreateUserDto fakeCreateUserDto = new CreateUserDtoBuilder()
                .withEyeColor(null).build();

        userService.createUser(fakeCreateUserDto);
    }
}