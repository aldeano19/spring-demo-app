package com.eriel.amex.demo.builder;

import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.MapAddress;

public class CreateUserDtoBuilder {
    String firstName = "John";
    String lastName = "Doe";
    String email = "john.doe@gmail.com";
    MapAddress address = new MapAddressBuilder().build();
    EyeColorEnum eyeColor = EyeColorEnum.BROWN;

    public CreateUserDtoBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public CreateUserDtoBuilder withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public CreateUserDtoBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public CreateUserDtoBuilder withAddress(MapAddress address){
        this.address = address;
        return this;
    }

    public CreateUserDtoBuilder withEyeColor(EyeColorEnum eyeColor){
        this.eyeColor = eyeColor;
        return this;
    }

    public CreateUserDto build(){
        return new CreateUserDto(firstName, lastName, email, address, eyeColor);
    }
}
