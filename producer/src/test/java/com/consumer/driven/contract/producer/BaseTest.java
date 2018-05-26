package com.consumer.driven.contract.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public abstract class BaseTest {

    @Autowired
    PersonController personController;

    @MockBean
    PersonService personService;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(personController);

        when(personService.findById("1"))
                .thenReturn(new Person("1", "TestName", "TestAddress"));
    }

}
