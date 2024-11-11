package com.employee.repository.custom;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Employee> getAllEmployeesByFilters(String query, Map<String, String> map) {
		
		TypedQuery<Employee> tq = em.createQuery(query, Employee.class);
		
		for(String str : map.keySet()) {
			tq.setParameter(str, map.get(str));
		}
		
		return tq.getResultList();
	}

}
