package com.consumer.driven.contract.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consume-person")
public class ConsumerController {

    private RestTemplate restTemplate;
    private static final String PRODUCER_URL = "http://localhost:8090/person/{id}";

    @Autowired
    public ConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Person getPerson(@PathVariable("id") String id) {
        System.out.printf("Request received at Consumer");
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity(PRODUCER_URL, Person.class, id);
        return responseEntity.getBody();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addPerson(@RequestBody Person person) {
        System.out.printf("Request received at Consumer");
        ResponseEntity<Person> responseEntity = restTemplate.postForEntity(PRODUCER_URL, person, Person.class);
        System.out.printf("Producer response: " + responseEntity.getStatusCode());
        System.out.println("Posted person to Producer: " + person);
    }
}
