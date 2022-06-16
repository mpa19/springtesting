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
import static org.assertj.core.api.InstanceOfAssertFactories.FLOAT;

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
    public void catADDCon0() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/add?a=0&b=2",
                String.class)).isEqualTo("0.0");
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

    @Test
    public void catSub() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/sub?a=1&b=2",
                String.class)).isEqualTo("-1.0");
    }

    @Test
    public void catMulti() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mult?a=1&b=2",
                String.class)).isEqualTo("2.0");
    }

    @Test
    public void catDivide() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div?a=1&b=2",
                String.class)).isEqualTo("0.5");
    }

    @Test
    public void catDivide00() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div?a=0&b=0",
                String.class)).contains("NaN");
    }

    @Test
    public void catDivideWith0() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div?a=1&b=0",
                String.class)).contains("Infinity");
    }

    @Test
    public void catDivideWithoutNumber() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div?a=1",
                String.class)).contains("Infinity");
    }

    @Test
    public void catDivideWitoutNumbers() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div",
                String.class)).contains("NaN");
    }

    @Test
    public void catDivideFloat() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div?a=1.5&b=2.5",
                String.class)).isEqualTo("0.6");
    }

    @Test
    public void catDivideNegative() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/div?a=5&b=-2",
                String.class)).isEqualTo("-2.5");
    }

    @Test
    public void catDivWithIncorrectNumber() {

        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/div?a=1&b=x",
                String.class).getStatusCode().is4xxClientError()).isTrue();
    }


    @Test
    public void catSubFloat() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/sub?a=1.5&b=2.5",
                String.class)).isEqualTo("-1.0");
    }

    @Test
    public void catSubNegative() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/sub?a=-5&b=2",
                String.class)).isEqualTo("-7.0");
    }

    @Test
    public void catSubWithIncorrectNumber() {

        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/sub?a=1&b=x",
                String.class).getStatusCode().is4xxClientError()).isTrue();
    }

    @Test
    public void catMultFloat() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mult?a=1.5&b=2.5",
                String.class)).isEqualTo("3.75");
    }

    @Test
    public void catMultNegative() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mult?a=-5&b=2",
                String.class)).isEqualTo("-10.0");
    }

    @Test
    public void catMultWithIncorrectNumber() {

        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/mult?a=1&b=x",
                String.class).getStatusCode().is4xxClientError()).isTrue();
    }


}