package com.lsy.vehicle.service;

import java.util.List;

import com.lsy.vehicle.domain.Engine;

public interface EngineService {
	
	public List<Engine> getAllEngines();

	public List<Engine> findByManufacturer(String manufacturerName);

	public void delete(Long engineId) throws EngineDoesNotExistException;

	public void createEngine(Engine engine);
	
}
