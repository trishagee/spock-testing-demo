package com.synacy.gradprogram.spock.demo

import spock.lang.Specification

class UserValidatorServiceSpec extends Specification {

    UserValidatorService service

    def setup() {
        service = new UserValidatorService()
    }

    def "isUserAgeValid should return #expectedValue"() {
        when:
        boolean actualValue = service.isUserAgeValid(age)

        then:
        expectedValue == actualValue

        where:
        age     | expectedValue
        21      | true
        11      | false
    }
}
