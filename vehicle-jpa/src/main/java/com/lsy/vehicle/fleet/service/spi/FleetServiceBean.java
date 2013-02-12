package com.lsy.vehicle.fleet.service.spi;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.dao.FleetDao;
import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.fleet.service.FleetService;
import com.lsy.vehicle.service.VehicleObserver;
import com.lsy.vehicle.service.VehicleService;

@Service
public class FleetServiceBean implements FleetService, VehicleObserver {
	
	@Autowired
	private FleetDao fleetDao;
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostConstruct
	private void initialize() {
//	    vehicleService.	
	}

	@Override
	public void addVehicles(String companyName, List<Vehicle> vehicleList) {
		Fleet fleet = fleetDao.findByCompanyName(companyName);
		if (fleet == null) {
			fleet = new Fleet();
			fleet.setCompanyName(companyName);
			fleetDao.create(fleet);
		}
		fleet.getVehicles().addAll(vehicleList);
	}

	@Override
	public Fleet findFleetByName(String companyName) {
		return fleetDao.findByCompanyName(companyName);
	}

    @Override
    public List<String> allCompanyNames() {
        return fleetDao.findAllCompanyNames();
    }

    @Override
    public void onVehicleDelete(Vehicle vehicle) {
        // TODO Auto-generated method stub
        
    }

}
