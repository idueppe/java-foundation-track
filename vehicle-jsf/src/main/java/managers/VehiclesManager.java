package managers;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dto.ManufacturerDto;
import com.lsy.vehicle.dto.VehicleDto;

/**
 * @author idueppe
 * @since 1.0
 */
@ManagedBean
@SessionScoped
public class VehiclesManager {

    private static final Logger LOG = Logger.getLogger(VehiclesManager.class.getName());

    @ManagedProperty("#{vehicleControllerBean}")
    private VehicleController vehicleController;
    private ManufacturerDto manufacturer;
    
    public List<VehicleDto> getVehicles() {
        if (manufacturer != null) {
            return vehicleController.findVehicleByManufacturer(manufacturer.getName());
        } else {
            return Collections.emptyList();
        }
    }

    public String showVehicles(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
        return "/views/vehicles";
    }

    public String delete(VehicleDto vehicleDto) {
        LOG.info("----------- DELETING VEHICLE...");
        vehicleController.deleteVehicle(vehicleDto);
        // TODO Hier eine Nachricht platzieren, dass das Fahrzeug gelöscht wurde.
        return "/views/vehicles";
    }

    public String addVehicle() {
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        msg.setSummary("Diese Funktion ist noch nicht implementiert.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/views/vehicles";
    }

    public String cancelAdding() {
        // TODO Hier eine Nachricht über den Abbruch absetzen.
        return "/views/vehicles";
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setVehicleController(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
    }
}
