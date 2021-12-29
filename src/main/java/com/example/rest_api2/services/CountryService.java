package com.example.rest_api2.services;

import com.example.rest_api2.controllers.Addresponse;
import com.example.rest_api2.models.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Component
public class CountryService {
    static HashMap<Long, Country> countryIdMap;

    public CountryService() {

        countryIdMap = new HashMap<Long, Country>();
        Country indiaCountry = new Country(1, "India", "Delhi");
        Country usaCountry = new Country(2, "USA", "Washington");
        Country ukCountry = new Country(3, "UK", "London");

        countryIdMap.put(1L, indiaCountry);
        countryIdMap.put(2L,usaCountry);
        countryIdMap.put(3L, ukCountry);
    }

    public List getAllCountries() {
        List countries = new ArrayList(countryIdMap.values());
        return countries;
    }

    public Country getCountryById(Long id){
        Country country = countryIdMap.get(id);
        return country;
    }

    public Country getCountryByName(String countryName){
        Country country = null;
        for(long i : countryIdMap.keySet()){
            if(countryIdMap.get(i).getCountryName().equals(countryName)){
                country =countryIdMap.get(i);
            }
        }
        return country;
    }

    public Country addCountry(Country country){
      country.setId(getMaxId());
      countryIdMap.put(country.getId(), country);
      return country;
    }

    //utility method to get max id
    public static long getMaxId(){
        long max = 0;
        for(long id: countryIdMap.keySet()){
            if(max <= id)
                max = id;
        }
        return max + 1;
    }

    public Country updateCountry(Country country){
        if(country.getId()>0)
            countryIdMap.put(country.getId(),country);
        return country;
    }

    public Addresponse deleteCountry(Long id){
        countryIdMap.remove(id);
        Addresponse res = new Addresponse();
        res.setMsg("COUNTRY HAS BEEN DELETED");
        res.setId(id);
        return res;
    }
}
