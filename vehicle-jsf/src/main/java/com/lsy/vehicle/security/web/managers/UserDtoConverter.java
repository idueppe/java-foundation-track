package com.lsy.vehicle.security.web.managers;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.security.dto.UserDto;


@FacesConverter("userDtoConverter")
public class UserDtoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value) && value.contains(":")) {
            UserDto userDto = new UserDto();
    
            String[] values = value.split(":");
    
            userDto.setUsername(values[0]);
            userDto.setFirstname(values[1]);
            userDto.setSurename(values[2]);
    
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof UserDto) {
            UserDto user = (UserDto) value;
            return user.getUsername() + ":" + user.getFirstname() + ":" + user.getSurename();
        }
        return "";
    }

}
