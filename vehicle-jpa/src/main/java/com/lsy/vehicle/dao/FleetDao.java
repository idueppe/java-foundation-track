package com.lsy.vehicle.dao;

import java.util.List;

import com.lsy.vehicle.domain.Fleet;

public interface FleetDao extends EntityDao<Fleet> {
	
	public Fleet findByCompanyName(String company);
	
	public List<String> findAllCompanyNames();
	
}
