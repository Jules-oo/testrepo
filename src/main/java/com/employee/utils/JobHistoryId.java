package com.employee.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.employee.model.Employee;

import jakarta.persistence.Embeddable;

@Embeddable
public class JobHistoryId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Employee employeeId;
	private Date startDate;
	
	public JobHistoryId() {}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistoryId that = (JobHistoryId) o;
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
