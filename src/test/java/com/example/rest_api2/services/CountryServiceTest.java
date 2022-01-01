package com.example.rest_api2.services;


import com.example.rest_api2.models.Country;
import com.example.rest_api2.repositories.CountryRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {CountryServiceTest.class})
class CountryServiceTest {

    @Mock
    CountryRepository countryRepo;

    @InjectMocks
    CountryService countryService;

    public List<Country> mycountries;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Order(1)
    void getAllCountries() {
        mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1, "India", "Delhi"));
        mycountries.add(new Country(2, "USA", "Washington"));
        when(countryRepo.findAll()).thenReturn(mycountries);//mocking statement
       assertEquals(2,countryService.getAllCountries().size());

    }

    @Test
    @Order(2)
    void getCountryById() {
        mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1L, "India", "Delhi"));
        mycountries.add(new Country(2L, "USA", "Washington"));
        Long countryID = 2L;
        when(countryRepo.findAll()).thenReturn(mycountries);
        assertEquals(countryID, countryService.getCountryById(countryID).getId());
    }

    @Test
    @Order(3)
    void getCountryByName() {
        mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1L, "India", "Delhi"));
        mycountries.add(new Country(2L, "USA", "Washington"));
        String countryName = "India";
        when(countryRepo.findAll()).thenReturn(mycountries);
        assertEquals(countryName, countryService.getCountryByName(countryName).getCountryName());
    }

    @Test
    @Order(4)
    void addCountry() {
        Country country = new Country(3L, "Germany", "Berlin");
        when(countryRepo.save(country)).thenReturn(country);
        assertEquals(country, countryService.addCountry(country));
    }

    @Test
    @Order(5)
    void updateCountry() {
        Country country = new Country(3L, "Germany", "Berlin");
        when(countryRepo.save(country)).thenReturn(country);
        assertEquals(country, countryService.updateCountry(country));
    }

    @Test
    @Order(6)
    void deleteCountry() {
        Country country = new Country(3L, "Germany", "Berlin");
        countryService.deleteCountry(country.getId());
      verify(countryRepo, times(1)).deleteById(country.getId());
    }
}