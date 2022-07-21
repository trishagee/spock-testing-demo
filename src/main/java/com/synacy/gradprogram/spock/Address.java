package com.synacy.gradprogram.spock;

import java.util.UUID;

public class Address {

    private final UUID id;
    private String country;
    private String city;
    private String street;

    public Address(String country, String city, String street) {
        this.id = UUID.randomUUID();
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public UUID getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String toString() {
        MockMethod();
        return "Country: " + country +
                ", City: " + city +
                ", Street: " + street;
    }

    public void MockMethod (){
        //do nothing
    }
}
