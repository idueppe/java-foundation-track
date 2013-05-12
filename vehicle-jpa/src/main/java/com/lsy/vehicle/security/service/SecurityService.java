package com.lsy.vehicle.security.service;

import java.util.List;

import com.lsy.vehicle.security.domain.FleetGroup;
import com.lsy.vehicle.security.domain.Role;
import com.lsy.vehicle.security.domain.User;
import com.lsy.vehicle.security.filter.UserFilterParameters;

public interface SecurityService {
    
    public void registerUser(User user);
    
    public List<User> findAllUser();
    
    public List<User> findAllCustomer();
    
    public List<User> findAllCustomerNotMemberOf(String companyName);
    
    public List<User> findByFilter(String username, String email, String firstname, String surename, Role role);
    
    public FleetGroup getGroupByCompanyName(String companyName);
    
    public void addUserToGroup(String companyName, String username);

    public List<User> findByFilter(UserFilterParameters filter);
    
}
