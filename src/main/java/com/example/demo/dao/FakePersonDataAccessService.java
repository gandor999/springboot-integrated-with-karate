package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

import org.springframework.stereotype.Repository;

@Repository("fakeDao") // semantics
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public Person insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return new Person(id, person.getName());
    }

    @Override
    public List<Person> getPeople() {
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = getPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        } else {
            DB.remove(personMaybe.get());
            return 1;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id).map(p -> {
            int indexOfPersonToDelete = DB.indexOf(person);
            if (indexOfPersonToDelete >= 0) {
                DB.set(indexOfPersonToDelete, person);
                return 1;
            } else {
                return 0;
            }
        }).orElse(0);
    }
}
