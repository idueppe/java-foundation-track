package com.lsy.vehicle.ui.managers;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.lsy.vehicle.controller.ManufacturerController;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.service.ManufacturerAlreadyExistsException;

/**
 * @author idueppe
 * @since 1.0
 */
@ManagedBean
@SessionScoped
public class ManufacturerManager {

    private static final Logger LOG = Logger.getLogger(ManufacturerManager.class.getName());

    @ManagedProperty(value="#{manufacturerControllerBean}")
    private ManufacturerController manufacturerController;

    private ManufacturerDto selectedManufacturer;

    private String uniqueManufacturerName;

    public List<ManufacturerDto> getAllManufacturers() {
        return manufacturerController.allManufactures();
    }

    public ManufacturerDto getManufacturer() {
        if (selectedManufacturer == null) {
            selectedManufacturer = new ManufacturerDto();
        }
        return selectedManufacturer;
    }

    public String getUniqueManufacturerName() {
        return uniqueManufacturerName;
    }

    public void validateManufacturerName(FacesContext context, UIComponent component, Object value) {
        if (doesManufacturerNameExist((String)value)) {
            ((UIInput) component).setValid(false);
            FacesMessage msg = new FacesMessage("Name existiert bereits.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(component.getClientId(), msg);
        } else {
            ((UIInput) component).setValid(true);
        }
    }

    private boolean doesManufacturerNameExist(String manufacturerName) {
        return manufacturerController.byName(manufacturerName).getId() != null;
    }

    public String selectForUpdate(ManufacturerDto manufacturer) {
        LOG.info("------- "+manufacturer.getName()+" ----------- SELECTED");
        this.selectedManufacturer = manufacturerController.byName(manufacturer.getName());
        return "/views/addmanufacturer";
    }
    
    public String startAddingNewManufacturer() {
        selectedManufacturer = new ManufacturerDto();
        return "/views/addmanufacturer";
    }

    public String addManufacturer() {
        FacesMessage msg = new FacesMessage();
        try {
            manufacturerController.addManufacturer(selectedManufacturer.getName());
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Neuer Herrsteller " + selectedManufacturer.getName() + " hinzugefügt.");
        } catch (ManufacturerAlreadyExistsException e) {
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            msg.setSummary("Es ist ein Fehler aufgetreten.");
            msg.setDetail(e.getMessage());
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/views/manufacturers";
    }
    
    public String updateManufacturer() {
        FacesMessage msg = new FacesMessage();
        manufacturerController.updateManufacturerName(selectedManufacturer);
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Name in "+selectedManufacturer.getName()+" geändert.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/views/manufacturers";
    }
    

    public String cancelAdding() {
        return "/views/manufacturers";
    }

    public void setManufacturerController(ManufacturerController manufacturerController) {
        this.manufacturerController = manufacturerController;
    }
    
    public boolean isModifying() {
        return getManufacturer().getId() != null;
    }

}
