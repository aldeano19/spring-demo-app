package com.eriel.amex.demo.dto;

import com.eriel.amex.demo.constants.EyeColorEnum;

/**
 * A Data Transfer Object used to gather data from the client and create a new User.
 */
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private PostalAddress address;
    private EyeColorEnum eyeColor;

    public CreateUserDto(String firstName, String lastName, String email, PostalAddress address, EyeColorEnum eyeColor) {
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

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
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
