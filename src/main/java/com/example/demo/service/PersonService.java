package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public Person addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getPeople() {
        return personDao.getPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.getPersonById(id);
    }

    public boolean deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public Person updatePersonById(UUID id, Person newPerson) {
        return personDao.updatePersonById(id, newPerson);
    }
}
