package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

import org.springframework.stereotype.Repository;

@Repository("fakeDao") // semantics
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>(); // the same as let array = [] in JS

    @Override
    public Person insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName())); // same as array.push(new Class()) in JS
        return new Person(id, person.getName());
    }

    @Override
    public List<Person> getPeople() {
        return DB; // same as return array in JS
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id))
                .findFirst(); // same as array.find(e => e == id)
    }

    @Override
    public boolean deletePersonById(UUID id) {
        Optional<Person> personMaybe = getPersonById(id);
        if (personMaybe.isEmpty()) {
            return false;
        } else {
            DB.remove(personMaybe.get()); // same as array.splice()
            return true;
        }
    }

    @Override
    public Person updatePersonById(UUID id, Person update) {
        return getPersonById(id).map(person -> {
            int indexOfPersonToDelete = DB.indexOf(person);
            if (indexOfPersonToDelete >= 0) {
                DB.set(indexOfPersonToDelete, new Person(id, update.getName())); // this whole thing is same as
                                                                                 // array.splice()
                return new Person(id, update.getName());
            } else {
                return null;
            }
        }).orElse(null);
    }
}
