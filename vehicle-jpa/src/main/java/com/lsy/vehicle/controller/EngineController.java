package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.EngineDto;

public interface EngineController {

    public List<EngineDto> byManufacturerName(String manufacturerName);
    
    public void addEngine(EngineDto engine);
    
    public void deleteEngine(EngineDto engine);
    
}
