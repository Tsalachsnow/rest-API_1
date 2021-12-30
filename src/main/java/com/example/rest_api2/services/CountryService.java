package com.example.rest_api2.services;

import com.example.rest_api2.controllers.Addresponse;
import com.example.rest_api2.models.Country;
import com.example.rest_api2.repositories.CountryRepository;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;

@Data
@Component
public class CountryService {
    static HashMap<Long, Country> countryIdMap;

    final CountryRepository countryRepo;

    public CountryService(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }


    public List getAllCountries() {
        return countryRepo.findAll();
    }

    public Country getCountryById(Long id){
        return countryRepo.findById(id).get();
    }

    public Country getCountryByName(String countryName){
        List<Country> countries = countryRepo.findAll();
        Country country = null;
        for(Country con : countries){
            if(con.getCountryName().equalsIgnoreCase(countryName))
                country = con;
        }
        return country;
    }

    public Country addCountry(Country country){
        country.setId(getMaxId());
        countryRepo.save(country);
        return country;
    }

    //utility method to get max id
    public long getMaxId(){
        return countryRepo.findAll().size()+1;
    }

    public Country updateCountry(Country country){
        countryRepo.save(country);
        return country;
    }

    public Addresponse deleteCountry(Long id) {
        countryRepo.deleteById(id);
        Addresponse res = new Addresponse();
        res.setMsg("Country Deleted!");
        res.setId(id);
        return res;
    }
}
