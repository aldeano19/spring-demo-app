package com.eriel.amex.demo.builder;

import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.PostalAddress;
import com.eriel.amex.demo.model.User;
import org.bson.types.ObjectId;

public class UserBuilder {

    ObjectId id = new ObjectId();
    String accountNumber = "A1234567890";
    String firstName = "John";
    String lastName = "Doe";
    String email = "john.doe@gmail.com";
    EyeColorEnum eyeColor = EyeColorEnum.BROWN;
    PostalAddress address = new PostalAddressBuilder().build();

    public UserBuilder withId(ObjectId objectId){
        this.id = objectId;
        return this;
    }

    public UserBuilder withAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
        return this;
    }

    public UserBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withEyeColor(EyeColorEnum eyeColor){
        this.eyeColor = eyeColor;
        return this;
    }

    public UserBuilder withAddress(PostalAddress address){
        this.address = address;
        return this;
    }

    public User buildFromCreateUserDto(CreateUserDto createUserDto){
        this.firstName = createUserDto.getFirstName();
        this.lastName = createUserDto.getLastName();
        this.email = createUserDto.getEmail();
        this.eyeColor = createUserDto.getEyeColor();
        this.address = createUserDto.getAddress();

        return build();
    }

    public User build(){
        return new User(id, accountNumber, firstName, lastName, email, address, eyeColor);
    }
}
