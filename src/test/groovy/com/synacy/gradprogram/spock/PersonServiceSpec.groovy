package com.synacy.gradprogram.spock

import spock.lang.Specification
import spock.lang.Subject

class PersonServiceSpec extends Specification {

    PersonRepository personRepository = Mock()
    EventPublisher eventPublisher = Mock()

    @Subject
    PersonService service = new PersonService(personRepository, eventPublisher)

    def "addPerson should save the new person."() {
        given:
        Address address = Mock()

        when:
        service.addPerson("Kenichi", 32, address, Sex.MALE)

        then:
        1 * personRepository.save(_) >> {Person passedPerson ->
            assert "Kenichi" == passedPerson.name
            assert 32 == passedPerson.age
            assert address == passedPerson.address
            assert Sex.MALE == passedPerson.sex
        }
    }
}
