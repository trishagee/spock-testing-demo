package com.mechanitis.demo.spock

import spock.lang.Specification
import spock.lang.Unroll

class ExampleSpecification extends Specification {
    def "should demonstrate a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {
        given:
        def shape = new Shape(4)

        when:
        int sides = shape.numberOfSides()

        then:
        sides == 4
    }

    @Unroll
    def "should demonstrate simple data driven testing. Number of sides: #expected"() {
        expect:
        shape.numberOfSides() == expected

        where:
        expected << [3, 4, 5, 8, 14]
        shape = new Shape(expected)
    }
}
