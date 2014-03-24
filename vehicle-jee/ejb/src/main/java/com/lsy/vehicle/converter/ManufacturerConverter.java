package com.lsy.vehicle.converter;

import javax.inject.Inject;

import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;

public class ManufacturerConverter extends AbstractDefaultConverter<Manufacturer, ManufacturerDto>{

    @Inject
    private VehicleConverter vehicleConverter;

    @Override
    protected ManufacturerDto newTargetInstance() {
        return new ManufacturerDto();
    }

    @Override
    protected void copyProperties(Manufacturer source, ManufacturerDto target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setVehicles(vehicleConverter.convert(source.getVehicles()));
    }

}
