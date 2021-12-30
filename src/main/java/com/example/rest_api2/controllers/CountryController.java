package com.example.rest_api2.controllers;



import com.example.rest_api2.models.Country;
import com.example.rest_api2.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CountryController {
    final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getcountries")
    public List<Country> getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") Long id){
        try {
            Country country = countryService.getCountryById(id);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName){
        try {
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country){
        return countryService.addCountry(country);
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") Long id, @RequestBody Country country){
        try{
            Country existCountry = countryService.getCountryById(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());
            Country country1 = countryService.updateCountry(existCountry);
            return new ResponseEntity<Country>(country1, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deletecountry/{id}")
    public Addresponse deleteCountry(@PathVariable(value="id") Long id){
        return countryService.deleteCountry(id);
    }
}
