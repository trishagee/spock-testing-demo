package com.mechanitis.demo.spock

import spock.lang.Specification

class ExampleSpecification extends Specification {
    def "should demonstrate a simple assertion"() {
        expect:
        1 == 1
    }
}
