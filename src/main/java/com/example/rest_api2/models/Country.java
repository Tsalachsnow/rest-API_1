package com.example.rest_api2.models;

import lombok.Data;

@Data
public class Country {

    private long id;
    private String countryName;
    private String countryCapital;

    public Country(long id, String countryName, String countryCapital) {
        this.id = id;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCountryName() {
//        return countryName;
//    }
//
//    public void setCountryName(String countryName) {
//        this.countryName = countryName;
//    }
//
//    public String getCountryCapital() {
//        return countryCapital;
//    }
//
//    public void setCountryCapital(String countryCapital) {
//        this.countryCapital = countryCapital;
//    }
}
