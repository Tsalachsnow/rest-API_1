package com.example.rest_api2.controllers;

import static org.mockito.Mockito.*;
import com.example.rest_api2.models.Country;
import com.example.rest_api2.services.CountryService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {CountryControllerTest.class})
class CountryControllerTest {

  @Mock
 CountryService countryService;

  @InjectMocks
  CountryController countryController;

 List<Country> mycountries;
 Country country;
    @BeforeEach
    void setUp() {

    }

    @Test
    @Order(1)
    void getCountries() {
        mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1L, "India", "Delhi"));
        mycountries.add(new Country(2L, "USA", "Washington"));
        when(countryService.getAllCountries()).thenReturn(mycountries);
        assertEquals(2,countryService.getAllCountries().size());
    }

    @Test
    @Order(2)
    void getCountryById() {
        country = new Country(2L, "USA", "Washington");
        Long CountryID = 2L;
        when(countryService.getCountryById(CountryID)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getCountryById(CountryID);
        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(CountryID, res.getBody().getId());

    }

    @Test
    @Order(3)
    void getCountryByName() {
        country = new Country(2L, "USA", "Washington");
        String countryName = "USA";
        when(countryService.getCountryByName(countryName)).thenReturn(country);
        ResponseEntity<Country> res = countryController.getCountryByName(countryName);
        assertEquals(HttpStatus.FOUND, res.getStatusCode());
        assertEquals(countryName, res.getBody().getCountryName());
    }

    @Test
    @Order(4)
    void addCountry() {
        country = new Country(3, "Germany", "Berlin");
        when(countryService.addCountry(country)).thenReturn(country);
        assertEquals(country, countryController.addCountry(country));
    }

    @Test
    @Order(5)
    void updateCountry() {
        country = new Country(3L, "Japan", "Tokyo");
        Long countryID = 3L;
        when(countryService.getCountryById(countryID)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);
        ResponseEntity<Country> res = countryController.updateCountry(countryID, country);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(3L, res.getBody().getId());
        assertEquals("Japan", res.getBody().getCountryName());
        assertEquals("Tokyo", res.getBody().getCountryCapital());

    }

    @Test
    @Order(6)
    void deleteCountry() {
        country = new Country(3L, "Japan", "Tokyo");
        Long countryID = 3L;
        when(countryService.getCountryById(countryID)).thenReturn(country);
        assertEquals(isNull(), countryController.deleteCountry(countryID));
    }
}