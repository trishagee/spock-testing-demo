package com.synacy.gradprogram.spock.demo

import spock.lang.Specification

class UnitTestDemoServiceSpec extends Specification {

    UnitTestDemoService service

    def setup() {
        service = new UnitTestDemoService()
    }

    def "sum should respond with the sum of the 2 given numbers"() {
        given:
        int num1 = 5
        int num2 = 2

        int expectedSum = 7

        when:
       int actualSum = service.sum(num1, num2)

        then:
        expectedSum == actualSum
    }

    def "sum should respond with the sum of the 2 given numbers using expect block"() {
        expect:
        7 == service.sum(5, 2)
    }

    def "calculateAverage should respond with the average value of the integers in the given list"() {
        given:
        List numbers = [1,3,5]

        double expectedAverage = 3

        when:
        double actualAverage = service.calculateAverage(numbers)

        then:
        expectedAverage == actualAverage
    }

    def "createUser should throw an AgeIsBelowAgeOfConsentException when the given age is less than 18"() {
        given:
        String firstName = "first name"
        String lastName = "last name"
        String address = "Cebu"
        int age = 21
        CivilStatus civilStatus = CivilStatus.SINGLE

        when:
        service.createUser(firstName, lastName, address, age, civilStatus)

        then:
        thrown(AgeIsBelowAgeOfConsentException)
    }

    def "createUser should respond with a User with the correct values"() {
        given:
        String firstName = "first name"
        String lastName = "last name"
        String address = "Cebu"
        int age = 21
        CivilStatus civilStatus = CivilStatus.SINGLE

        when:
        User createdUser = service.createUser(firstName, lastName, address, age, civilStatus)

        then:
        firstName == createdUser.getFirstName()
        lastName == createdUser.getLastName()
        address == createdUser.getAddress()
        age == createdUser.getAge()
        civilStatus == createdUser.getCivilStatus()
    }
}
