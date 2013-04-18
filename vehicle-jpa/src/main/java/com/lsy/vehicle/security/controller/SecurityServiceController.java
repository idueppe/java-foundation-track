package com.lsy.vehicle.security.controller;

import java.util.List;

import com.lsy.vehicle.security.dto.FleetGroupDto;
import com.lsy.vehicle.security.dto.UserDto;

public interface SecurityServiceController {

    public void registerUser(UserDto userDto);

    public List<UserDto> findAllUsers();

    public List<UserDto> findAllCustomer();

    public List<UserDto> findAllCustomersNotMemberOf(String companyName);

    public List<UserDto> findByFilter(String username, String email, String firstname,
                    String surename, String role);

    public FleetGroupDto getGroupByCompanyName(String companyName);

    public void addUserToGroup(String companyName, String username);

}
