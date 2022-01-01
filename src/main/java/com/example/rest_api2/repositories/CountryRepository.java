package com.example.rest_api2.repositories;

import com.example.rest_api2.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}