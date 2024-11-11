package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Job;

public interface JobRepository extends JpaRepository<Job, String>{

}
