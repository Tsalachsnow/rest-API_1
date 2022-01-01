package com.example.rest_api2.controllers;

import static org.mockito.Mockito.*;

import com.example.rest_api2.models.Country;
import com.example.rest_api2.services.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.example.rest_api2")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {CountryControllerMockMvcTest.class})
class CountryControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    CountryService countryService;

    @InjectMocks
    CountryController countryController;

    List<Country> mycountries;
    Country country;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
    }

    @Test
    @Order(1)
    void getCountries() throws Exception {
      mycountries = new ArrayList<>();
      mycountries.add(new Country(1L, "India", "Delhi"));
      mycountries.add(new Country(2L, "USA", "Washington"));

      when(countryService.getAllCountries()).thenReturn(mycountries);
      this.mockMvc.perform(MockMvcRequestBuilders.get("/getcountries"))
              .andExpect(status().isFound()).andDo(print());
    }

    @Test
    @Order(2)
    void getCountryById() throws Exception {
        country = new Country(2L, "USA", "Washington");
        Long countryID = 2L;
        when(countryService.getCountryById(countryID)).thenReturn(country);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/getcountries/{id}", countryID))
                .andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath(".id").value(2)).
        andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
                .andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washington"))
                 .andDo(print());
    }

    @Test
    @Order(3)
    void getCountryByName() throws Exception {
        country = new Country(2L, "USA", "Washington");
        String countryName = "USA";
        when(countryService.getCountryByName(countryName)).thenReturn(country);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/getcountries/countryname").param("name", countryName))
                .andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath(".id").value(2)).
                andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
                .andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washington"))
                .andDo(print());

    }

    @Test
    @Order(4)
    void addCountry() throws Exception {
        country = new Country(3L, "Germany", "Berlin");
        when(countryService.addCountry(country)).thenReturn(country);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(country);

        this.mockMvc.perform(post("/addcountry").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    @Order(5)
    void updateCountry() throws Exception {
        country = new Country(3L, "Japan", "Tokyo");
        Long countryID = 3L;
        when(countryService.getCountryById(countryID)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(country);
        this.mockMvc.perform(put("/updatecountry/{id}", countryID).content(jsonBody).contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Japan"))
        .andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Tokyo"))
         .andDo(print());
    }

    @Test
    @Order(6)
    void deleteCountry() throws Exception {
        country = new Country(3L, "Japan", "Tokyo");
        Long countryID = 3L;
        when(countryService.getCountryById(countryID)).thenReturn(country);
        this.mockMvc.perform(delete("/deletecountry/{id}", countryID)).andExpect(status().isOk());
    }
}