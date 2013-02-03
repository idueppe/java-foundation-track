package com.lsy.vehicle.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;


@Entity
@NamedQueries(
	value = {
	     @NamedQuery(name=Fleet.FIND_BY_COMPANY_NAME, query="SELECT f FROM Fleet f WHERE f.companyName = :companyName"),
	     @NamedQuery(name=Fleet.FIND_ALL, query="SELECT f FROM Fleet f")
	}
)
@Table(uniqueConstraints=@UniqueConstraint(name="companyNameUniqueConstraint", columnNames="companyName") )
public class Fleet {
    
    public static final String FIND_BY_COMPANY_NAME = "Fleet.findByCompanyName";
    public static final String FIND_ALL = "Fleet.findAll";
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String companyName;
	
	@OneToMany()
	private List<Vehicle> vehicles;
	
	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Vehicle> getVehicles() {
		if (vehicles == null) {
			vehicles = new ArrayList<Vehicle>();
		}
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


}
