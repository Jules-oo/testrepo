package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.dto.SelectDTO;
import com.employee.repository.custom.SelectRepository;

@Service
@Transactional
public class SelectService {

	@Autowired
	private SelectRepository selectRepo;
	
	public List<SelectDTO> getHierarchy() {
		return selectRepo.find();
	}
	
}
