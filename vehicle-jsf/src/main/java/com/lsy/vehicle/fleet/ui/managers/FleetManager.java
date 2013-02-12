package com.lsy.vehicle.fleet.ui.managers;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dto.FleetVehicleDto;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.dto.VehicleDto;
import com.lsy.vehicle.fleet.controller.VehicleFleetCart;

@ManagedBean
@SessionScoped
public class FleetManager{
    
    private static final Logger LOG = Logger.getLogger(FleetManager.class.getName());

    @ManagedProperty(value="#{manufacturerControllerBean}")
    private ManufacturerController manufacturerController;
    
    @ManagedProperty(value="#{vehicleControllerBean}")
    private VehicleController vehicleController;
    
    @ManagedProperty(value="#{vehicleFleetCartBean}")
    private VehicleFleetCart fleetCart;
    
    private ManufacturerDto selectedManufacturer;
    
    private VehicleDto selectedVehicle;
    
    private String companyName;
    
    public List<ManufacturerDto> getAvailableManufacturers()  {
        return manufacturerController.allManufactures();
    }
    
    public List<VehicleDto> getAvailableVehicles() {
        if (selectedManufacturer != null) {
            return selectedManufacturer.getVehicles();
        } else {
            return Collections.emptyList();
        }
    }
    
    public List<FleetVehicleDto> getFleet() {
        return fleetCart.listCart();
    }
    
    public String addVehicle() {
        LOG.info("Adding selected vehicle "+selectedVehicle.getModelName()+" to fleet.");
        
        FleetVehicleDto fleetVehicle = new FleetVehicleDto();
        fleetVehicle.setVehicleId(selectedVehicle.getId());
        fleetVehicle.setVehicleModel(selectedVehicle.getModelName());
        fleetVehicle.setOrderDate(new Date());
        fleetVehicle.setManufacturerName(selectedVehicle.getManufacturerName());
        fleetVehicle.setConstructionDate(selectedVehicle.getConstructionDate());
        
        fleetCart.add(fleetVehicle);
        
        selectedVehicle = null;
        selectedManufacturer = null;
        
        return null;
    }

    public String delete(FleetVehicleDto vehicle) {
        LOG.info("Deleting vehicle "+vehicle.getVehicleModel()+" from car.");
        fleetCart.remove(vehicle);
        
        return null;
    }
    
    @PostConstruct
    public void postConstruct() {
        LOG.info("Opening new fleet cart");
    }
    
    @PreDestroy
    public void preDestroy() {
        LOG.info("Closing fleet cart");
        fleetCart.close();
        // cause misleading log statement see https://issues.jboss.org/browse/AS7-5077
        fleetCart = null;
    }
    
    public String order() {
        fleetCart.order(companyName);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().put("fleetManager", null);
        
        // Different variations of removing a session scoped bean
        
//        facesContext.getApplication().createValueBinding("#{fleetManager}").setValue(facesContext, null);
//        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
//        ValueExpression valueExpression = expressionFactory.createValueExpression("#{fleetManager}", FleetManager.class);
//        valueExpression.setValue(facesContext.getELContext(), null);
        
        return "/views/companies";
    }
    
    public void setSelectedManufacturer(ManufacturerDto selectedManufacturer) {
        LOG.info("Manufacturer is selected "+selectedManufacturer);
        if (selectedManufacturer != null) {
            this.selectedManufacturer = manufacturerController.byName(selectedManufacturer.getName());
        } else {
            this.selectedManufacturer = null;
        }
        this.selectedVehicle = null;
    }
    
    public ManufacturerDto getSelectedManufacturer() {
        return selectedManufacturer;
    }
    
    public VehicleDto getSelectedVehicle() {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleDto selectedVehicle) {
        LOG.info("Vehicle is selected "+selectedVehicle);
        if (selectedVehicle != null) {
            this.selectedVehicle = vehicleController.getVehicle(selectedVehicle.getId());
        } else {
            this.selectedVehicle = null;
        }
            
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public ManufacturerController getManufacturerController() {
        return manufacturerController;
    }

    public void setManufacturerController(ManufacturerController manufacturerController) {
        this.manufacturerController = manufacturerController;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }

    public void setVehicleController(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
    }

    public VehicleFleetCart getFleetCart() {
        return fleetCart;
    }

    public void setFleetCart(VehicleFleetCart fleetCart) {
        this.fleetCart = fleetCart;
    }
    

}

