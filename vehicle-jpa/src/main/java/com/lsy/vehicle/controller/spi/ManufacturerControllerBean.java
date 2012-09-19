package com.lsy.vehicle.controller.spi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.converter.ManufacturerConverter;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerService;

@Service
public class ManufacturerControllerBean implements ManufacturerController {
    
    @Autowired
    private ManufacturerService manufacturerService;
    
    @Autowired
    private ManufacturerConverter manufacturerConverter;

    @Override
    @Transactional
    public ManufacturerDto byName(String manufacturerName) {
        Manufacturer manufacturer = manufacturerService.byName(manufacturerName);
        return manufacturerConverter.convert(manufacturer);
    }

    @Override
    public List<ManufacturerDto> allManufactures() {
        // TODO Bitte implementiert diese Methode.
        return null;
    }

    @Override
    public void addManufacturer(String manufacturerName) {
        // TODO Bitte implementiert diese Methode.
    }

    @Override
    public void deleteManufacturer(String manufacturerName) {
        // TODO Bitte implementiert diese Methode.
    }

}
