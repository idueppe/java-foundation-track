package com.lsy.vehicle.security.dto;

import java.io.Serializable;
import java.util.List;

public class FleetGroupDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String companyName;
    private List<UserDto> users;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

}
