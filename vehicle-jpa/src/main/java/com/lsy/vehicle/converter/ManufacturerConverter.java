package com.lsy.vehicle.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;

@Service("manufacturerConverter")
public class ManufacturerConverter extends AbstractDefaultConverter<Manufacturer, ManufacturerDto>{

    @Autowired
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
