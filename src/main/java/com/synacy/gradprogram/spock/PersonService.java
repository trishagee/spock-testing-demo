package com.synacy.gradprogram.spock;

import java.util.UUID;

public class PersonService {

    private final PersonRepository personRepository;
    private final EventPublisher eventPublisher;

    public PersonService(PersonRepository personRepository, EventPublisher eventPublisher) {
        this.personRepository = personRepository;
        this.eventPublisher = eventPublisher;
    }

    public void addPerson(String name, int age, Address address, Sex sex) {
        Person person = new Person(name, age, address, sex);

        personRepository.save(person);

        eventPublisher.publishMessage("Saved person >> " + person);

    }

    public void deletePerson(UUID id) {
        Person person = personRepository.findById(id);

        personRepository.delete(person);

        eventPublisher.publishMessage("Deleted person >> " + person);
    }
}
