package com.consumer.driven.contract.consumer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerApplicationTests {

    @Rule
    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
            .downloadStub("com.consumer.driven.contract", "producer", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8081)
            .workOffline(true);

    @Test
    public void get_person_from_service_contract() {
        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        ResponseEntity<Person> personResponseEntity = restTemplate.getForEntity("http://localhost:8081/person/1", Person.class);

        // then:
        assertThat(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(personResponseEntity.getBody().getId()).isEqualTo("1");
        assertThat(personResponseEntity.getBody().getName()).isEqualTo("TestName");
        assertThat(personResponseEntity.getBody().getAddress()).isEqualTo("TestAddress");
    }

    @Test
    public void post_person_from_service_contract() {
        // given:
        RestTemplate restTemplate = new RestTemplate();

        // when:
        ResponseEntity<Person> personResponseEntity = restTemplate.postForEntity("http://localhost:8081/person", new Person("1", "TestName", "TestAddress"), Person.class);

        // then:
        assertThat(personResponseEntity.getStatusCodeValue()).isEqualTo(202);
    }

}
