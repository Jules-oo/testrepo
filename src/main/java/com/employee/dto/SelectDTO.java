package com.employee.dto;

import java.io.Serializable;

// struttura dati composita per l'inoltro al client della mappatura gerarchica
// dep. -> location -> country -> region

// viene richiamata dal client all'avvio

public class SelectDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer departmentId;
	private String departmentName;

	private Integer locationId;
	private String city;

	private String countryId;
	private String countryName;

	private Integer regionId;
	private String regionName;

	public SelectDTO() {
	}

	public SelectDTO(Integer departmentId, String departmentName, Integer locationId, String city, String countryId,
			String countryName, Integer regionId, String regionName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.locationId = locationId;
		this.city = city;
		this.countryId = countryId;
		this.countryName = countryName;
		this.regionId = regionId;
		this.regionName = regionName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return "SelectDTO [departmentId=" + departmentId + ", departmentName=" + departmentName + ", locationId="
				+ locationId + ", city=" + city + ", countryId=" + countryId + ", countryName=" + countryName
				+ ", regionId=" + regionId + ", regionName=" + regionName + "]";
	}

	
	
}
