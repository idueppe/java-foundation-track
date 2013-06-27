package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.EngineDto;
import com.lsy.vehicle.service.EngineDoesNotExistException;

public interface EngineController {

    public List<EngineDto> byManufacturerName(String manufacturerName);
    
    public void addEngine(EngineDto engine);
    
    public void deleteEngine(EngineDto engine) throws EngineDoesNotExistException;
    
}
