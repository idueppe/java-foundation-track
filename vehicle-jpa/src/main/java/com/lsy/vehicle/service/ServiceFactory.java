package com.lsy.vehicle.service;

import com.lsy.vehicle.service.spi.VehicleServiceBean;

public class ServiceFactory {
	
	public static VehicleService getVehicleService() {
		return new VehicleServiceBean();
	}
	
}
