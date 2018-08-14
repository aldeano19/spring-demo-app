package com.eriel.amex.demo.dto;

public class MinimalUserInfoDto {
    private String firstName;
    private String lastName;
    private PostalAddress address;

    public MinimalUserInfoDto() {}

    public MinimalUserInfoDto(String firstName, String lastName, PostalAddress address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
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
}
