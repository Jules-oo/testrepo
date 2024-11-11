package com.employee.repository.custom;

import java.util.List;

import com.employee.dto.SelectDTO;

public interface SelectRepository {

	List<SelectDTO> find();
	
}
