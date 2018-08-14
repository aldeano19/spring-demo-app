package com.eriel.amex.demo.builder;

import com.eriel.amex.demo.dto.PostalAddress;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;

public class MinimalUserInfoDtoBuilder {
    String firstName = "John";
    String lastName = "Doe";
    PostalAddress address = new PostalAddressBuilder().build();

    public MinimalUserInfoDtoBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public MinimalUserInfoDtoBuilder withLasttName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public MinimalUserInfoDtoBuilder withAddressName(PostalAddress address){
        this.address = address;
        return this;
    }

    public MinimalUserInfoDto build(){
        return new MinimalUserInfoDto(firstName, lastName, address);
    }
}
