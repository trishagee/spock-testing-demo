package com.mechanitis.demo.spock

import spock.lang.Specification

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

    def "should demonstrate simple data driven testing"() {
        expect:
        shape.numberOfSides() == expected

        where:
        expected << [3, 4, 5, 8, 14]
        shape = new Shape(expected)
    }
}
