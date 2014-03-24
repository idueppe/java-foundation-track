package com.lsy.vehicle.fleet.controller;

import java.util.List;

import com.lsy.vehicle.dto.FleetVehicleDto;

public interface VehicleFleetController {
	
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName);

	public void addVehicles(String companyName, List<FleetVehicleDto> vehicleList);
	
	public List<String> allCompanyNames();

}
