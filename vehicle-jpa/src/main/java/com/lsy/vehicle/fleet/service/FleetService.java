package com.lsy.vehicle.fleet.service;

import java.util.List;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.domain.Fleet;

public interface FleetService {

	void addVehicles(String companyName, List<Vehicle> vehicleList);

	Fleet findFleetByName(String companyName);

    List<String> allCompanyNames();

}
