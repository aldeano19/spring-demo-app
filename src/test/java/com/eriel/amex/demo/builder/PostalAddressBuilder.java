package com.eriel.amex.demo.builder;

        import com.eriel.amex.demo.dto.PostalAddress;

public class PostalAddressBuilder {
    public String streetAddress = "123 NE 45 ST";
    public String city = "Miami";
    public String zipcode = "33334";
    public String country = "United States";

    public PostalAddressBuilder withStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
        return this;
    }

    public PostalAddressBuilder withCity(String city){
        this.city = city;
        return this;
    }

    public PostalAddressBuilder withZipcode(String zipcode){
        this.zipcode = zipcode;
        return this;
    }

    public PostalAddressBuilder withCountry(String country){
        this.country = country;
        return this;
    }

    public PostalAddress build(){
        return new PostalAddress(streetAddress, city, zipcode, country);
    }
}
