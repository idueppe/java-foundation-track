package com.lsy.vehicle.service;

import com.lsy.vehicle.domain.Vehicle;

public interface VehicleObserver {
    
    public void onVehicleDelete(Vehicle vehicle);

}
