package com.eriel.amex.demo.builder;

        import com.eriel.amex.demo.dto.MapAddress;

public class MapAddressBuilder {
    public String streetAddress = "123 NE 45 ST";
    public String city = "Miami";
    public String zipcode = "33334";
    public String country = "United States";

    public MapAddressBuilder withStreetAddress(String streetAddress){
        this.streetAddress = streetAddress;
        return this;
    }

    public MapAddressBuilder withCity(String city){
        this.city = city;
        return this;
    }

    public MapAddressBuilder withZipcode(String zipcode){
        this.zipcode = zipcode;
        return this;
    }

    public MapAddressBuilder withCountry(String country){
        this.country = country;
        return this;
    }

    public MapAddress build(){
        return new MapAddress(streetAddress, city, zipcode, country);
    }
}
