package com.employee.repository.custom;

import java.util.List;
import java.util.Map;

import com.employee.model.Employee;

public interface EmployeeRepositoryCustom {

	List<Employee> getAllEmployeesByFilters(String query, Map<String, String> map);

}
