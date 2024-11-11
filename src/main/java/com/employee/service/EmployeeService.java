package com.employee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.dto.EmployeeForm;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public Employee add(Employee e) {
		return employeeRepo.save(e);
	}
	
	public Employee update(int id) {
		Employee e = employeeRepo.findById(id).get();
		return employeeRepo.save(e);
	}
	
	public List<Employee> findAll() {
		return employeeRepo.findAll();
	}
	
	public Employee findById(int id) {
		return employeeRepo.findById(id).get();
	}
	
	
	public List<Employee> buildQuery(EmployeeForm eForm) {
		StringBuilder str = new StringBuilder();
		List<String> conditions = new ArrayList<>(); // blocchi della WHERE
		Map<String, String> params = new HashMap<>(); // parametri per JPQL
		
		str.append("SELECT e FROM Employee e");
		
		// controllo dei parametri dell'oggetto EmployeeForm
		if (eForm.getMaxSalary() != null) {
			conditions.add("e.salary <= :maxsalary");
			params.put("maxsalary", eForm.getMaxSalary().toString());
		}
		
		if (eForm.getMinSalary() != null) {
			conditions.add("e.salary >= :minsalary");
			params.put("minsalary", eForm.getMinSalary().toString());
		}
		
		if (!eForm.getName().isEmpty()) {
			conditions.add("e.firstName = :name");
			params.put("name", eForm.getName());
		}
		
		// dobbiamo recuperare il nome del dipartimento sulla join
		if (!eForm.getDepartment().isEmpty()) {
			conditions.add("e.department.departmentName = :dep");
			params.put("dep", eForm.getDepartment());
		}
		
		// department -> location -> country -> region
		
		if (!eForm.getLocation().isEmpty()) {
			conditions.add("e.department.location.city = :loc");
			params.put("loc", eForm.getLocation());
		}
		
		if (!eForm.getCountry().isEmpty()) {
			conditions.add("e.department.location.country.countryName = :country");
			params.put("country", eForm.getCountry());
		}
		
		if (!eForm.getRegion().isEmpty()) {
			conditions.add("e.department.location.country.region.regionName = :region");
			params.put("region", eForm.getRegion());
		}
		
		// build della WHERE clause (se presenti condizioni)
		if (!conditions.isEmpty()) {
			str.append(" WHERE ").append(String.join(" AND ", conditions));
		}
		
		String query = str.toString();
		
		// controllo 
		System.out.println(conditions);
		System.out.println(params);
		System.out.println(query);
		
		return this.getAllEmployeesByFilters(query, params);
	}
	
	public List<Employee> getAllEmployeesByFilters(String query, Map<String, String> map) {
		return employeeRepo.getAllEmployeesByFilters(query, map);
	}
	

	
	
}
