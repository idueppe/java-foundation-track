package com.lsy.vehicle.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;

@Service
@WebService
public class ManufacturerWS {

    @Autowired
    private ManufacturerController controller;
    
    @WebMethod
    public List<ManufacturerDto> allManufactures()
    {
        return controller.allManufactures();
    }
    
    @WebMethod
    public void addManufacturer(String manufacturerName) throws ManufacturerAlreadyExistsException
    {
        controller.addManufacturer(manufacturerName);
    }
    
}
