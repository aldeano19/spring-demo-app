package com.eriel.amex.demo.controller;

import com.eriel.amex.demo.DemoApplication;
import com.eriel.amex.demo.builder.CreateUserDtoBuilder;
import com.eriel.amex.demo.builder.MinimalUserInfoDtoBuilder;
import com.eriel.amex.demo.builder.UserBuilder;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;
import com.eriel.amex.demo.model.User;
import com.eriel.amex.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Mock
    private UserService userServiceMock;



    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getMinimalUserInfo_UserIsFoundByAccountNumber_ResponseIfMinimalUserData() throws Exception {

        final MinimalUserInfoDto fakeUserInfoResult = new MinimalUserInfoDtoBuilder().build();

        when(userServiceMock.getMinimalUserInfo(Mockito.any(String.class))).thenReturn(fakeUserInfoResult);

        String fakeAccount = "A1234567890";

        String path = "/users/info/"+fakeAccount;

        mockMvc.perform(get(path))
                .andExpect(status().isOk()) // Check status 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) // Check response is JSON

                // No need to check the whole address, one key is enough to determine if address is not null
                .andExpect(jsonPath("$.address.streetAddress", is(fakeUserInfoResult.getAddress().getStreetAddress())))
                .andExpect(jsonPath("$.lastName", is(fakeUserInfoResult.getLastName())))
                .andExpect(jsonPath("$.firstName", is(fakeUserInfoResult.getFirstName())));

        // Verify userServiceMock.getMinimalUserInfo(fakeAccount) was called only once
        verify(userServiceMock, times(1)).getMinimalUserInfo(fakeAccount);

        // Verify no pending interactions for userServiceMock
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void create_SuccessfulInsertion_TheResponseIsNewUser() throws Exception {
        final CreateUserDto createUserDto = new CreateUserDtoBuilder().build();
        final User newFakeUserAfterInsertion = new UserBuilder().withFirstName(createUserDto.getFirstName())
                .withLastName(createUserDto.getLastName())
                .withAddress(createUserDto.getAddress())
                .withEmail(createUserDto.getEmail()).build();

        when(userServiceMock.createUser(Mockito.any(CreateUserDto.class))).thenReturn(newFakeUserAfterInsertion);

        String path = "/users";

        mockMvc.perform(post(path))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(newFakeUserAfterInsertion.getId().toString())))
                .andExpect(jsonPath("$.accountNumber", is(newFakeUserAfterInsertion.getAccountNumber())))
                .andExpect(jsonPath("$.firstName", is(newFakeUserAfterInsertion.getFirstName())));

        verify(userServiceMock, times(1)).createUser(Mockito.any(CreateUserDto.class));
        verifyNoMoreInteractions(userServiceMock);
    }
}