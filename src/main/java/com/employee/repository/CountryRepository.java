package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Country;

public interface CountryRepository extends JpaRepository<Country, String>{

}
