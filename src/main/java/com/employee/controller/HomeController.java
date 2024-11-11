package com.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeForm;
import com.employee.dto.SelectDTO;
import com.employee.model.Employee;
import com.employee.repository.CountryRepository;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.LocationsRepository;
import com.employee.repository.RegionRepository;
import com.employee.service.EmployeeService;
import com.employee.service.SelectService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	EmployeeService eService;
	@Autowired
	SelectService selectService;
	
	
	@Autowired
	CountryRepository countryRepo;
	@Autowired
	LocationsRepository locRepo;
	@Autowired
	RegionRepository regRepo;
	@Autowired
	DepartmentRepository depRepo;
	
	@GetMapping("/home/hierarchy")
	public ResponseEntity<List<SelectDTO>> getHierarchy(HttpServletRequest request) {
	    List<SelectDTO> hierarchy = selectService.getHierarchy();
	    return ResponseEntity.ok(hierarchy);
	}

	@GetMapping("/home")
	public String home(Model model, HttpServletRequest request) {
		
		EmployeeForm employeeForm = new EmployeeForm();
		model.addAttribute("employeeForm", employeeForm);

		List<String> countries = new ArrayList<>();
		List<String> regions = new ArrayList<>();
		List<String> departments = new ArrayList<>();
		List<String> locations = new ArrayList<>();

		countryRepo.findAll().forEach(c -> {
			countries.add(c.getCountryName());
		});

		regRepo.findAll().forEach(r -> {
			regions.add(r.getRegionName());
		});

		depRepo.findAll().forEach(d -> {
			departments.add(d.getDepartmentName());
		});

		locRepo.findAll().forEach(l -> {
			locations.add(l.getCity());
		});
		
		model.addAttribute("locations", locations);
		model.addAttribute("countries", countries);
		model.addAttribute("regions", regions);
		model.addAttribute("departments", departments);
		
		return "home";
	}

	@PostMapping("/home/search")
	public String get(Model model, @ModelAttribute("employeeForm") EmployeeForm eForm, HttpServletRequest request) {

		List<EmployeeDTO> toRet = new ArrayList<>();
		List<Employee> results = eService.buildQuery(eForm);
		
		toRet = results.stream()
				.map(EmployeeDTO::fromEntity)
				.collect(Collectors.toList());
		
		model.addAttribute("searchResults", toRet);
	    model.addAttribute("employeeForm", eForm);

		if (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").equals("XMLHttpRequest")) { // se la richiesta è fatta con jQuery
			return "fragments/results :: resultsTable";
		}
		return "home";
	}

}
