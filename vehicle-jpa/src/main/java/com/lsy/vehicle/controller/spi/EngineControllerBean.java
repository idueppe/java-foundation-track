package com.lsy.vehicle.controller.spi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.controller.EngineController;
import com.lsy.vehicle.converter.EngineConverter;
import com.lsy.vehicle.converter.EngineDtoConverter;
import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.dto.EngineDto;
import com.lsy.vehicle.service.EngineDoesNotExistException;
import com.lsy.vehicle.service.EngineService;

@Service
public class EngineControllerBean implements EngineController {

	@Autowired
	private EngineService engineService;
	
	@Autowired
	private EngineConverter engineConverter;
	
	@Autowired
	private EngineDtoConverter engineDtoConverter;
	
	
	@Override
	public List<EngineDto> byManufacturerName(String manufacturerName) {
		List<Engine> engines = engineService.findByManufacturer(manufacturerName);
		return engineConverter.convert(engines);
	}
	
	@Override
	public void addEngine(EngineDto engine) {
		engineService.createEngine(engineDtoConverter.convert(engine));
	}

	@Override
	public void deleteEngine(EngineDto engine) throws EngineDoesNotExistException {
		engineService.delete(engine.getEngineId());
	}

}
