package com.lsy.vehicle.controller;

import java.util.List;

import com.lsy.vehicle.dto.FleetVehicleDto;

public interface VehicleFleetCart {

    List<FleetVehicleDto> listCart();

    void remove(FleetVehicleDto vehicle);

    void close();

    void add(FleetVehicleDto fleetVehicle);

    void order(String companyName);

}
