package com.lsy.vehicle.dao;

import com.lsy.vehicle.domain.Manufacturer;

public interface ManufacturerDao extends EntityDao<Manufacturer> {
    
    public Manufacturer findManufacturerByName(String name);

}
