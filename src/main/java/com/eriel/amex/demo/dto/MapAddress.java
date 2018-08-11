package com.eriel.amex.demo.dto;

public class MapAddress {
    private String streetAddress;
    private String city;
    private String zipcode;
    private String country;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        String out = String.format("Street Address: %s; City: %s; Zip Code: %s; Country: %s;",
                streetAddress, city, zipcode, country);
        return out;
    }
}
