package com.lsy.vehicle.dao;

import java.util.List;

import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Manufacturer;

public interface ManufacturerDao extends EntityDao<Manufacturer> {
    
    public Manufacturer findManufacturerByName(String name);
    
    public List<Manufacturer> findManufacturerWithEngineTypes(EngineType... engineType);

}
