package com.synacy.gradprogram.spock;

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

        eventPublisher.publishMessage(person.toString());
    }
}
