package com.lsy.vehicle.fleet.converter;

import org.springframework.stereotype.Service;

import com.lsy.vehicle.converter.AbstractDefaultConverter;
import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.dto.FleetVehicleDto;

@Service
public class FleetVehicleDtoConverter extends AbstractDefaultConverter<Vehicle, FleetVehicleDto> {

	@Override
	protected FleetVehicleDto newTargetInstance() {
		return new FleetVehicleDto();
	}

	@Override
	protected void copyProperties(Vehicle source, FleetVehicleDto target) {
		target.setConstructionDate(source.getConstructionDate());
		target.setManufacturerName(source.getManufacturer().getName());
		// TODO OrderDate
		target.setVehicleModel(source.getModel());
	}

}
