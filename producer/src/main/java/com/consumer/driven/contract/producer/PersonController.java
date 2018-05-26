package com.consumer.driven.contract.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Randeep on 24/05/2018.
 */

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Person getPerson(@PathVariable("id") String id) {
        System.out.printf("Request received at Producer");
        return personService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void addPerson(@RequestBody Person person) {
        System.out.printf("Request received at Producer");
        System.out.println("Posted person: " + person);
        personService.add(person);
    }
}