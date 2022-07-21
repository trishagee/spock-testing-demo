package com.synacy.gradprogram.spock

import spock.lang.Specification
import spock.lang.Unroll

class PersonSpec extends Specification {
    static int personAge = 0;
    static Sex personSex = Sex.MALE;
    Address address = new Address("CountryName", "CityName", "StreetName")
    Person person = new Person("PersonName", personAge, address, personSex)

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
        Person TestAge = new Person("PersonName", expectedAge, address, personSex)

        then:
        TestAge.getAge() == expectedAge


        where:
        expectedAge << (1..100)


    }

    @Unroll
    def "setAge() should return expected int age: #expectedAge"() {

        when:
        Person TestAge = new Person("PersonName", 12, address, personSex)
        TestAge.setAge(expectedAge)

        then:
        TestAge.getAge() == expectedAge

        where:
        expectedAge << (1..50)
    }

    def "getAddress() should return group of string address being set"() {
        given:
        Address TestAddress = new Address("Japan", "Tokyo", "Yokohama")

        when:
        Person TestPerson = new Person("TestPersonName", personAge, TestAddress, personSex)

        then:
        TestPerson.getAddress() == TestAddress
    }

    def "setAddress() should return expected address"() {
        given:
        Address TestAddress = new Address("Philippines", "Manila", "Tondo")

        when:
        Person TestPerson = new Person("TestPersonName", personAge, address, personSex)
        TestPerson.setAddress(TestAddress)

        then:
        TestPerson.getAddress() == TestAddress
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
        Address TestAddress = new Address("Philippines", "Cebu City", "Mabolo")

        when:
        Person TestPerson = new Person("Maria Dela Cruz", 23, TestAddress, Sex.FEMALE)

        then:
        String expectedString = "Name: Maria Dela Cruz, Age: 23, Address: (Country: Philippines, City: Cebu City, Street: Mabolo), Sex: FEMALE"
        TestPerson.toString() == expectedString

    }
}
