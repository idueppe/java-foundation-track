package com.lsy.vehicle.fleet.service.spi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.dao.FleetDao;
import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.fleet.service.FleetService;

@Service
public class FleetServiceBean implements FleetService {
	
	@Autowired
	private FleetDao fleetDao;

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

}
