package com.synacy.gradprogram.spock

import spock.lang.Specification

class AddressSpec extends Specification {
    def "getCountry() should return the expected country address"() {
        given:
        Address address = new Address("Philippines", "Cebu", "Kasambagan")

        when:
        String countryLocation = address.getCountry()

        then:
        countryLocation == "Philippines"

    }

    def "setCountry() should return the name of the country being set"() {

        when:
        Address address = new Address("Philippines", "Maasin", "Mari Clara")
        address.setCountry("Japan")

        then:
        address.getCountry() == "Japan"

    }

    def "getCity() should return the expected city address"() {
        given:
        Address address = new Address("Philippines", "Maasin", "Mantahan")

        when:
        String cityLocation = address.getCity()


        then:
        cityLocation == "Maasin"
    }

    def "setCity() should return the name of the city being set "() {
        when:
        Address address = new Address("Philippines", "Maasin", "Mantahan")
        address.setCity("Cebu City")

        then:
        address.getCity() == "Cebu City"
    }

    def "getStreet() should return the expected street name"() {
        given:
        Address address = new Address("Philippines", "Cebu City","Kasambagan")

        when:
        String streetName = address.getStreet()

        then:
        streetName ==  "Kasambagan"
    }

    def "setStreet() should return the name of the street being set"() {
        when:
        Address address = new Address("Philippines", "Cebu City", "Talamban")
        address.setStreet("Guadalupe")

        then:
        address.getStreet() == "Guadalupe"

    }

    def "toString() should return a string with the Country, City, and Street in a single phrase"() {

        given:
        Address address = new Address("Philippines", "Maasin City", "Mantahan")

        when:
        String expectedToString = address.toString()

        then:
        expectedToString == "Country: Philippines, City: Maasin City, Street: Mantahan"

    }
}
