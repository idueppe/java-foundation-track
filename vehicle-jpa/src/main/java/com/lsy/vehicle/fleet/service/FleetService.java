package com.lsy.vehicle.fleet.service;

import java.util.List;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.domain.Fleet;

public interface FleetService {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	/**
	 * 
	 * @param companyName
	 * @return Fleet instance of Fleet or NULL if company not exist.
	 */
	Fleet findFleetByName(String companyName);

	/**
	 * Never null.
	 * @return List<String> list of company names
	 */
    List<String> allCompanyNames();

}
