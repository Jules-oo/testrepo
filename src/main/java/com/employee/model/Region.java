package com.employee.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "regions")
public class Region {

	@Id
	private int regionId;

	@Column(name = "region_name")
	private String regionName;

	@OneToMany(mappedBy = "region")
	private List<Country> listaCountry;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<Country> getListaCountry() {
		return listaCountry;
	}

	public void setListaCountry(List<Country> listaCountry) {
		this.listaCountry = listaCountry;
	}

	public Region() {
		super();
	}

}