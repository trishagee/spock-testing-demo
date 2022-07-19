package com.synacy.gradprogram.spock;

import java.util.UUID;

public interface PersonRepository {

    public void save(Person person);

    public void delete(Person person);

    public Person findById(UUID id);
}
