package com.employee.repository.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.employee.dto.SelectDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SelectRepositoryImpl implements SelectRepository {

	@PersistenceContext
	EntityManager em;

	/*
	 * 
	select d.department_name, d.department_id, l.city, l.location_id, c.country_name, c.country_id, r.region_name, r.region_id
	from departments d join locations l on d.location_id = l.location_id 
	join countries c on c.country_id = l.country_id
	join regions r on r.region_id = c.region_id
	 * 
	 * */
	
	@Override
	public List<SelectDTO> find() {
		String jpql =  "SELECT NEW com.employee.dto.SelectDTO(" + 
				"d.departmentId, " + "d.departmentName, " + "l.locationId, " + "l.city, " + "c.countryId, "
				+ "c.countryName, " + "r.regionId, " + "r.regionName) " + "FROM Department d " + "JOIN d.location l "
				+ "JOIN l.country c " + "JOIN c.region r";
		
		String query = """ 
				SELECT NEW com.employee.dto.SelectDTO( 
				d.departmentId, d.departmentName, l.locationId, l.city, c.countryId, c.countryName,
				 r.regionId, r.regionName) FROM Department d JOIN d.location l JOIN l.country c JOIN c.region r
				""";

		return em.createQuery(query, SelectDTO.class).getResultList();
	}

}
