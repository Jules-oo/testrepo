package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Employee;
import com.employee.repository.custom.EmployeeRepositoryCustom;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeRepositoryCustom{

}
