package com.lsy.vehicle.dao;

import java.util.List;

import com.lsy.vehicle.domain.Engine;

public interface EngineDao extends EntityDao<Engine>{

	List<Engine> findByManufacturer(String manufacturerName);

}
