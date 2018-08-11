package com.eriel.amex.demo.builder;

import com.eriel.amex.demo.dto.MapAddress;
import com.eriel.amex.demo.dto.MinimalUserInfoDto;

public class MinimalUserInfoDtoBuilder {
    String firstName = "John";
    String lastName = "Doe";
    MapAddress address = new MapAddressBuilder().build();

    public MinimalUserInfoDtoBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public MinimalUserInfoDtoBuilder withLasttName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public MinimalUserInfoDtoBuilder withAddressName(MapAddress address){
        this.address = address;
        return this;
    }

    public MinimalUserInfoDto build(){
        return new MinimalUserInfoDto(firstName, lastName, address);
    }
}
