package com.lsy.vehicle.security.web.managers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import com.lsy.vehicle.security.controller.SecurityServiceController;
import com.lsy.vehicle.security.dto.UserDto;

@ManagedBean
@SessionScoped
public class UserManager {

    @ManagedProperty(value = "#{securityServiceControllerBean}")
    private SecurityServiceController securityController;

    private UserDto selectedUser;

    private UserDto filter = new UserDto();

    public List<UserDto> getAllUsers() {
        return securityController.findByFilter(
                        wild(filter.getUsername()), 
                        wild(filter.getEmail()),
                        wild(filter.getFirstname()), 
                        wild(filter.getSurename()), 
                        filter.getRole());
    }

    private String wild(String value) {
        if (StringUtils.isNotBlank(value)) {
            return "%" + value + "%";
        } else {
            return value;
        }
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
        return "/views/secure/users";
    }

    public String cancelAdding() {
        selectedUser = null;
        return "/views/secure/users";
    }

    public UserDto getFilter() {
        return filter;
    }

    public void setFilter(UserDto filter) {
        this.filter = filter;
    }

    public SecurityServiceController getSecurityController() {
        return securityController;
    }

    public void setSecurityController(SecurityServiceController securityController) {
        this.securityController = securityController;
    }

}
