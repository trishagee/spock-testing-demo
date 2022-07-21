package com.synacy.gradprogram.spock

import spock.lang.Specification
import spock.lang.Unroll

class PersonSpec extends Specification {
    static int age = 0;
    static Sex sex = Sex.MALE;
    Address address = new Address("CountryName", "CityName", "StreetName")
    Person person = new Person("PersonName", age, address, sex)

    def "getName() should return string name being set"() {
        when:
        person.setName("Zuckerberg")

        then:
        person.getName() == "Zuckerberg"
    }

    def "setName() should return expected name"() {
        when:
        person.setName("Enrique")
        String resultOfGetName = person.getName()

        then:
        resultOfGetName == "Enrique"
    }

    @Unroll
    def "getAge() should return int age being set: #expectedAge"() {
        when:
        Person testAge = new Person("PersonName", expectedAge, address, sex)

        then:
        testAge.getAge() == expectedAge


        where:
        expectedAge << (1..100)


    }

    @Unroll
    def "setAge() should return expected int age: #expectedAge"() {

        when:
        Person testAge = new Person("PersonName", 12, address, sex)
        testAge.setAge(expectedAge)

        then:
        testAge.getAge() == expectedAge

        where:
        expectedAge << (1..50)
    }

    def "getAddress() should return group of string address being set"() {
        given:
        Address testAddress = new Address("Japan", "Tokyo", "Yokohama")

        when:
        Person TestPerson = new Person("TestPersonName", age, testAddress, sex)

        then:
        TestPerson.getAddress() == testAddress
    }

    def "setAddress() should return expected address"() {
        given:
        Address testAddress = new Address("Philippines", "Manila", "Tondo")

        when:
        Person TestPerson = new Person("TestPersonName", age, address, sex)
        TestPerson.setAddress(testAddress)

        then:
        TestPerson.getAddress() == testAddress
    }

    def "getSex() should return sex object being set"() {
        when:
        person.setSex(Sex.FEMALE)

        then:
        person.getSex() == Sex.FEMALE

    }

    def "setSex() should return expected sex: #expectedSex"() {
        when:
        person.setSex(expectedSex)

        then:
        person.getSex() == expectedSex

        where:
        expectedSex << [Sex.MALE, Sex.FEMALE]
    }

    def "toString() should return a string with the Name, Age, Address, and Sex as single phrase"() {
        given:
        Address testAddress = new Address("Philippines", "Cebu City", "Mabolo")

        when:
        Person testPerson = new Person("Maria Dela Cruz", 23, testAddress, Sex.FEMALE)

        then:
        String expectedString = "Name: Maria Dela Cruz, Age: 23, Address: (Country: Philippines, City: Cebu City, Street: Mabolo), Sex: FEMALE"
        testPerson.toString() == expectedString

    }

}
