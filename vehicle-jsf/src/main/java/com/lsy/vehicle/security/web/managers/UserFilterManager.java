package com.lsy.vehicle.security.web.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.lsy.vehicle.security.controller.SecurityServiceController;
import com.lsy.vehicle.security.dto.UserDto;
import com.lsy.vehicle.security.filter.UserFilterParameters;

@ManagedBean
@ViewScoped
public class UserFilterManager{
    
    @ManagedProperty("#{securityServiceControllerBean}")
    private SecurityServiceController securityController;
    
    private UserDto selectedUser = new UserDto();
    
    private UserFilterParameters filter = new UserFilterParameters();
    
    public void resetFilter() {
        filter = new UserFilterParameters();
    }
    
    public List<UserDto> getAllUsers() {
        System.out.println("reload list...");
        return securityController.findByFilter(filter);
    }
    
    public UserDto getSelectedUser() {
        return selectedUser;
    }
    
    public String startAddingNewUser() {
        selectedUser = new UserDto();
        return "/views/secure/adduser";
    }

    public String addUser() {
        securityController.registerUser(selectedUser);
        return "/views/secure/userfilter";
    }
    
    public String cancelAdding() {
        selectedUser = null;
        return "/views/secure/userfilter";
    }

    public UserFilterParameters getFilter() {
        return filter;
    }

    public void setSecurityController(SecurityServiceController securityController) {
        this.securityController = securityController;
    }

}

