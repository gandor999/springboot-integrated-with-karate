package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

public interface PersonDao {
    Person insertPerson(UUID id, Person person);

    default Person insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> getPeople();

    Optional<Person> getPersonById(UUID id);

    Person updatePersonById(UUID id, Person person);

    boolean deletePersonById(UUID id);

}
