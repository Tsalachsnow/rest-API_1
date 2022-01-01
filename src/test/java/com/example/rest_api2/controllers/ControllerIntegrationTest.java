package com.example.rest_api2.controllers;

import com.example.rest_api2.models.Country;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ControllerIntegrationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @Order(1)
    void getCountries() throws JSONException {
        String expected = "[\r\n"
                + "    {\r\n"
                + "        \"id\": 1, \r\n"
                + "         \"countryName\": \"India\",\r\n"
                + "         \"countryCapital\": \"Delhi\"\r\n"
                + "     },\r\n"
                + "        {\r\n"
                + "        \"id\": 2, \r\n"
                + "         \"countryName\": \"USA\",\r\n"
                + "         \"countryCapital\": \"Washington\"\r\n"
                + "     },\r\n"
                + "        {\r\n"
                + "        \"id\": 3, \r\n"
                + "         \"countryName\": \"Germany\",\r\n"
                + "         \"countryCapital\": \"Berlin\"\r\n"
                + "     }\r\n"
                + " ]";


        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(2)
    void getCountryById() throws JSONException {
        String expected = "{\r\n"
                + "        \"id\": 1,\r\n"
                + "         \"countryName\": \"India\",\r\n"
                + "         \"countryCapital\": \"Delhi\"\r\n"
                + "     }";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/1", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);


    }

    @Test
    @Order(3)
    void getCountryByName() throws JSONException {
        String expected = "{\r\n"
                + "        \"id\": 1,\r\n"
                + "         \"countryName\": \"India\",\r\n"
                + "         \"countryCapital\": \"Delhi\"\r\n"
                + "     }";
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/1", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    @Order(4)
    void addCountry() throws JSONException {

        String expected = "{\r\n"
                + "        \"id\": 4,\r\n"
                + "         \"countryName\": \"Japan\",\r\n"
                + "         \"countryCapital\": \"Tokyo\"\r\n"
                + "     }";
        Country country = new Country(4, "Japan", "Tokyo");
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
       ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);
       System.out.println(response.getBody());
       assertEquals(OK, response.getStatusCode());
       JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    @Order(5)
    void updateCountry() throws JSONException {
        String expected = "{\r\n"
                + "        \"id\": 4,\r\n"
                + "         \"countryName\": \"Japan\",\r\n"
                + "         \"countryCapital\": \"Tokyo\"\r\n"
                + "     }";
        Country country = new Country(4, "Japan", "Tokyo");
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/updatecountry/4", HttpMethod.PUT, request, String.class);
        System.out.println(response.getBody());
        assertEquals(OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    @Order(6)
    void deleteCountry() throws JSONException {
        String expected = "{\r\n"
                + "        \"id\": 3,\r\n"
                + "         \"countryName\": \"Japan\",\r\n"
                + "         \"countryCapital\": \"Tokyo\"\r\n"
                + "     }";
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete("http://localhost:8080/deletecountry/3");
//        Country country = new Country(3, "Japan", "Tokyo");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
//        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/deletecountry/3", HttpMethod.DELETE, request, String.class);
//        System.out.println(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        JSONAssert.assertEquals(expected, response.getBody(), false);


    }
}