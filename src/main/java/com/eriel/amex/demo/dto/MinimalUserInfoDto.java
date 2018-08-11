package com.eriel.amex.demo.dto;

public class MinimalUserInfoDto {
    private String firstName;
    private String lastName;
    private MapAddress address;

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
}
