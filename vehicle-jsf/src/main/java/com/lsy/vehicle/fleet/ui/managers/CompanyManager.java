package com.lsy.vehicle.fleet.ui.managers;

import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.fleet.controller.VehicleFleetController;

@ManagedBean
@SessionScoped
public class CompanyManager {

    @ManagedProperty(value="#{vehicleFleetControllerBean}")
    private VehicleFleetController fleetController;
    
    private String selectedCompany;

    
    public String getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(String selectedCompany) {
        this.selectedCompany = selectedCompany;
    }
    
    public List<String> getCompanies() {
        return fleetController.allCompanyNames();
    }
    
    public List<FleetVehicleDto> getFleet() {
        if (StringUtils.isNotBlank(selectedCompany)) {
            return fleetController.getVehicleFleetByName(selectedCompany);
        } else {
            return Collections.emptyList();
        }
    }
    
    public String deleteVehicle(FleetVehicleDto vehicle) {
//        fleetController.deleteVehicle(selectedCompany, vehicle);
        return "";
    }

    public void setFleetController(VehicleFleetController fleetController) {
        this.fleetController = fleetController;
    }
    
}
