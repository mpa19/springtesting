package com.example.springtesting;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greet",
                String.class)).contains("Hello, World");
    }

    @Test
    public void catADD() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/add?a=1&b=2",
                String.class)).isEqualTo("3.0");
    }

    @Test
    public void catADDWithIncorrectNumber() {

        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/add?a=1&b=x",
                String.class).getStatusCode().is4xxClientError()).isTrue();
    }

    @Test
    public void catADDWithoutNumber() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/add?a=1",
                String.class)).isEqualTo("1.0");
    }
}