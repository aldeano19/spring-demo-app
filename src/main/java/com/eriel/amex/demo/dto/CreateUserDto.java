package com.eriel.amex.demo.dto;

import com.eriel.amex.demo.constants.EyeColorEnum;

/**
 * A POJO to insert a new User into the database.
 */
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private MapAddress address;
    private EyeColorEnum eyeColor;

    public CreateUserDto(String firstName, String lastName, String email, MapAddress address, EyeColorEnum eyeColor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.eyeColor = eyeColor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MapAddress getAddress() {
        return address;
    }

    public void setAddress(MapAddress address) {
        this.address = address;
    }

    public EyeColorEnum getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColorEnum eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
