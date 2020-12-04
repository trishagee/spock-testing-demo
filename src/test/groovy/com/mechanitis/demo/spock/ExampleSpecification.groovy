package com.mechanitis.demo.spock

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ExampleSpecification extends Specification {

    @Subject
    Shape theTestSubject = new Shape(56)

    def "should demonstrate a simple assertion"() {
        expect:
        1 == 1
    }

    // can use just when-then if there's no setup
    def "should demonstrate given-when-then"() {
        given:
        def shape = new Shape(4)

        when:
        int sides = shape.numberOfSides()

        then:
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Shape(0)

        then:
        // no need for .class
        def e = thrown(TooFewSidesException)
        // no need for get
        e.numberOfSides == 0
    }

    @Unroll
    def "should demonstrate simple data driven testing. Number of sides: #expected"() {
        expect:
        shape.numberOfSides() == expected

        where:
        expected << [3, 4, 5, 8, 14]
        shape = new Shape(expected)
    }

    def "should demonstrate data tables"() {

    }

    def "should be able to mock a concrete class"() {
        given:
        Renderer renderer = Mock()
        def shape = new Shape(4, renderer)

        when:
        shape.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to use a stub"() {
        given:
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red
    }
}
