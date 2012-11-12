package com.lsy.vehicle.converter;

import org.springframework.stereotype.Service;

import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.dto.EngineDto;

@Service("engineDtoConverter")
public class EngineDtoConverter extends AbstractDefaultConverter<EngineDto, Engine>{

	@Override
	protected Engine newTargetInstance() {
		return new Engine();
	}

	@Override
	protected void copyProperties(EngineDto source, Engine target) {
		target.setId(source.getEngineId());
		target.setModel(source.getModel());
		target.setType(source.getEngineType());
	}
	 
}
