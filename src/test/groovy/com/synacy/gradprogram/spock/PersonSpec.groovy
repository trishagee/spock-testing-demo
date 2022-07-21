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
//
//    def "test getAddress"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test setAddress"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test getSex"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test setSex"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test toString"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
}
