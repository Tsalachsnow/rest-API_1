package com.example.rest_api2.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_capital")
    private String countryCapital;

    public Country(long id, String countryName, String countryCapital) {
        this.id = id;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public Country() {

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
