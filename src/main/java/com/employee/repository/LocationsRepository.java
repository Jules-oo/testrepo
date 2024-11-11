package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Location;

public interface LocationsRepository extends JpaRepository<Location, Integer>{

}
