package com.lsy.vehicle.ui.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.dto.VehicleDto;


@FacesConverter("vehicleDtoConverter")
public class VehicleDtoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value) && value.contains(":")) {
            VehicleDto vehicleDto = new VehicleDto();
    
            String[] values = value.split(":");
    
            vehicleDto.setId(Long.parseLong(values[0]));
            vehicleDto.setModelName(values[1]);
    
            return vehicleDto;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof VehicleDto) {
            VehicleDto vehicle = (VehicleDto) value;
            return vehicle.getId() + ":" + vehicle.getModelName();
        }
        return "";
    }

}
