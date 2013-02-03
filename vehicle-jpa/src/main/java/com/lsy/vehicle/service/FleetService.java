package com.lsy.vehicle.service;

import com.lsy.vehicle.domain.Fleet;
import com.lsy.vehicle.domain.Vehicle;

import java.util.List;

public interface FleetService {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	Fleet findFleetByName(String companyName);

    List<String> allCompanyNames();

}
