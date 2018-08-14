package com.eriel.amex.demo.model;

import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.PostalAddress;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    @JsonSerialize(using = ToStringSerializer.class) // Converts ObjectId to string when returning it to the client
    private ObjectId id;

    @Indexed(unique = true)
    private String accountNumber;

    private String firstName;
    private String lastName;
    private String email;
    private PostalAddress address;
    private EyeColorEnum eyeColor;

    public User(){}

    public User(ObjectId id, String accountNumber, String email, String firstName, String lastName, PostalAddress address, EyeColorEnum eyeColor) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.eyeColor = eyeColor;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public EyeColorEnum getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColorEnum eyeColor) {
        this.eyeColor = eyeColor;
    }
}
