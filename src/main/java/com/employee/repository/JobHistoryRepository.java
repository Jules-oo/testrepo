package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.JobHistory;
import com.employee.utils.JobHistoryId;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {

}
