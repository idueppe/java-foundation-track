package com.lsy.vehicle.ui.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.dto.ManufacturerDto;

@FacesConverter("manufacturerDtoConverter")
public class ManufacturerDtoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value) && value.contains(":")) {
            ManufacturerDto manufacturerDto = new ManufacturerDto();
            
            String[] values = value.split(":");
            
            manufacturerDto.setId(Long.parseLong(values[0]));
            manufacturerDto.setName(values[1]);
            
            return manufacturerDto;
        } else  {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof ManufacturerDto) {
            ManufacturerDto manufacturer = (ManufacturerDto)value;
            // FIXME idueppe - encode manufacturer name
            return manufacturer.getId()+":"+ manufacturer.getName();
        } else {
            return "";
        }
    }

}
