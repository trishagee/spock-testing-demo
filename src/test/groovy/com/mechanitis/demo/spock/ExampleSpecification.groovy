package com.mechanitis.demo.spock

import spock.lang.Specification

class ExampleSpecification extends Specification {

    def "should demonstrate a simple assertion"() {
        expect:
        1 == 1
    }
}

//def "should demonstrate given-when-then"() {
//    given:
//    def shape = new Polygon(4)
//
//    when:
//    int sides = shape.getNumberOfSides()
//
//    then:
//    sides == 4
//}
//
//def "should expect Exceptions"() {
//    when:
//    new Polygon(0)
//
//    then:
//    def e = thrown(TooFewSidesException)
//    e.numberOfSides == 0
//}
//
//def "should expect Exceptions for more than one value"() {
//    when:
//    new Polygon(sides)
//
//    then:
//    def e = thrown(TooFewSidesException)
//    e.numberOfSides == sides
//
//    where:
//    sides << [0, 1, 2]
//}
//
//
//def "should demonstrate simple data driven testing. Number of sides: #expected"() {
//    expect:
//    shape.getNumberOfSides() == expected
//
//    where:
//    expected << [3, 4, 5, 8, 14]
//    shape = new Polygon(expected)
//}
//
//def "should demonstrate data tables. Max of #a and #b should be #c"() {
//    expect:
//    Math.max(a, b) == c
//
//    where:
//    a | b || c
//    1 | 3 || 3
//    7 | 4 || 7
//    0 | 0 || 0
//}
//



