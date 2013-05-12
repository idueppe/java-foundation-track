package com.lsy.vehicle.security.controller.spi;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsy.vehicle.security.controller.SecurityServiceController;
import com.lsy.vehicle.security.converter.FleetGroupConverter;
import com.lsy.vehicle.security.converter.UserConverter;
import com.lsy.vehicle.security.domain.FleetGroup;
import com.lsy.vehicle.security.domain.Role;
import com.lsy.vehicle.security.domain.User;
import com.lsy.vehicle.security.dto.FleetGroupDto;
import com.lsy.vehicle.security.dto.UserDto;
import com.lsy.vehicle.security.filter.UserFilterParameters;
import com.lsy.vehicle.security.service.SecurityService;

@Service
@Transactional
public class SecurityServiceControllerBean implements SecurityServiceController {
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserConverter userConverter;
    
    @Autowired
    private FleetGroupConverter fleetGroupConverter;

    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstname(userDto.getFirstname());
        user.setSurename(userDto.getSurename());
        user.setEmail(userDto.getEmail());
        user.setRole(Role.valueOf(userDto.getRole()));
        securityService.registerUser(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userConverter.convert(securityService.findAllUser());
    }

    @Override
    public List<UserDto> findAllCustomer() {
        return userConverter.convert(securityService.findAllCustomer());
    }

    @Override
    public FleetGroupDto getGroupByCompanyName(String companyName) {
        FleetGroup group = securityService.getGroupByCompanyName(companyName);
        return fleetGroupConverter.convert(group);
    }

    @Override
    public void addUserToGroup(String companyName, String username) {
        securityService.addUserToGroup(companyName, username);
    }
    
    @Override
    public List<UserDto> findAllCustomersNotMemberOf(String companyName) {
        return userConverter.convert(securityService.findAllCustomerNotMemberOf(companyName));
    }

    @Override
    public List<UserDto> findByFilter(String username, String email, String firstname,
                    String surename, String roleName) {
        Role role = null;
        if (StringUtils.isNotBlank(roleName)) {
            role = Role.valueOf(roleName);
        }
        return userConverter.convert(securityService.findByFilter(username, email, firstname, surename, role));
    }

    @Override
    public List<UserDto> findByFilter(UserFilterParameters filter) {
        return userConverter.convert(securityService.findByFilter(filter));
    }
}
