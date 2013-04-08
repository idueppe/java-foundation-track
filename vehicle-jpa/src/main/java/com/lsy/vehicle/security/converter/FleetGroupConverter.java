package com.lsy.vehicle.security.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lsy.vehicle.converter.AbstractDefaultConverter;
import com.lsy.vehicle.security.domain.FleetGroup;
import com.lsy.vehicle.security.dto.FleetGroupDto;

@Component
public class FleetGroupConverter extends AbstractDefaultConverter<FleetGroup, FleetGroupDto>{
    
    @Autowired
    private UserConverter userConverter;

    @Override
    protected FleetGroupDto newTargetInstance() {
        return new FleetGroupDto();
    }

    @Override
    protected void copyProperties(FleetGroup source, FleetGroupDto target) {
        target.setCompanyName(source.getFleet().getCompanyName());
        target.setUsers(userConverter.convert(source.getUsers()));
    }
    
}
