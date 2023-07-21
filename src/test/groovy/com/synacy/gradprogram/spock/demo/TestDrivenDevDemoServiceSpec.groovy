package com.synacy.gradprogram.spock.demo

import spock.lang.Specification

class TestDrivenDevDemoServiceSpec extends Specification {

    TestDrivenDevDemoService service

    def setup() {
        service = new TestDrivenDevDemoService()
    }

    def "sum should return with the sum of the two given numbers"() {
        given:
        int num1 = 1
        int num2 = 2

        when:
        double result = service.sum(num1, num2)

        then:
        3d == result
    }

    def "divide should return the quotient of the given dividend and divisor"() {
        given:
        double dividend = 4
        double divisor = 2

        when:
        double quotient = service.divide(dividend, divisor)

        then:
        2d == quotient
    }

    def "divide should throw InfinityOperationException when the divisor is 0"() {
        given:
        double dividend = 4
        double divisor = 0

        when:
        service.divide(dividend, divisor)

        then:
        thrown(InfinityOperationException)
    }
}
