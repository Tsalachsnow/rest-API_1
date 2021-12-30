package com.example.rest_api2.controllers;



import com.example.rest_api2.models.Country;
import com.example.rest_api2.services.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getcountries")
    public List getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/getcountries/{id}")
        public Country getCountryById(@PathVariable(value = "id") Long id){
        return countryService.getCountryById(id);
        }

        @GetMapping("/getcountries/countryname")
    public Country getCountryByName(@RequestParam(value = "name") String countryName){
        return countryService.getCountryByName(countryName);
        }

        @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country){
        return countryService.addCountry(country);
        }

        @PutMapping("/updatecountry")
    public Country updateCountry(@RequestBody Country country){
        return countryService.updateCountry(country);
        }

        @DeleteMapping("/deletecountry/{id}")
    public Addresponse deleteCountry(@PathVariable(value="id") Long id){
        return countryService.deleteCountry(id);
        }
}
