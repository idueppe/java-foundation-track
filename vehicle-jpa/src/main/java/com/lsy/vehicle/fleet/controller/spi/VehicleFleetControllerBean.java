package com.lsy.vehicle.fleet.controller.spi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.fleet.controller.VehicleFleetController;
import com.lsy.vehicle.fleet.converter.FleetVehicleDtoConverter;
import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.fleet.service.FleetService;
import com.lsy.vehicle.service.VehicleService;

@Service()
@Transactional
public class VehicleFleetControllerBean implements VehicleFleetController {
	
	@Autowired
	private FleetService fleetService;

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private FleetVehicleDtoConverter fleetVehicleDtoConverter;

	@Override
	public List<FleetVehicleDto> getVehicleFleetByName(String companyName) {
		Fleet fleet = fleetService.findFleetByName(companyName);
		if (fleet != null)
		{
		    return fleetVehicleDtoConverter.convert(fleet.getVehicles());
		} else {
		    return new LinkedList<>(); 
		}
	}

	@Override
	public void addVehicles(String companyName, List<FleetVehicleDto> vehicleList) {

		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		for (FleetVehicleDto dto : vehicleList) {
			Vehicle vehicle = vehicleService.getVehicleById(dto.getVehicleId());
			vehicles.add(vehicle);
		}
		
		fleetService.addVehicles(companyName, vehicles);
	}

    @Override
    public List<String> allCompanyNames() {
        return fleetService.allCompanyNames();
    }

}
