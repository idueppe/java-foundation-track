package com.lsy.vehicle.dao;

import java.util.List;

import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Vehicle;

public interface VehicleDao extends EntityDao<Vehicle> {

    public List<Vehicle> findVehicleByManufacturer(String name);

    public Vehicle findCheapestVehicle();
    
    public List<Vehicle> findVehiclesByEngineType(EngineType engineType);

}
