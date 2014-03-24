package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;

public interface ManufacturerController {

    public ManufacturerDto byName(String manufacturerName);
    
    public List<ManufacturerDto> allManufactures();
    
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException;
    
    public void deleteManufacturer(String manufacturerName);

    public void updateManufacturerName(ManufacturerDto selectedManufacturer);
    
}
