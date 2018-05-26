package com.consumer.driven.contract.producer;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {

    private static Map<String, Person> persons;

    public PersonService() {
        persons = new HashMap<>();
        persons.put("1", new Person("1", "TestName", "TestAddress"));
        persons.put("2", new Person("2", "SampleName", "SampleAddress"));
    }


    public Person findById(String id) {
        return persons.get(id);
    }

    public void add(Person person) {
        persons.put(person.getId(), person);
    }
}
