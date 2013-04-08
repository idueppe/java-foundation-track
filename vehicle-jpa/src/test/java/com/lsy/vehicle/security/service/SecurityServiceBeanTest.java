package com.lsy.vehicle.security.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lsy.vehicle.fleet.dao.FleetDao;
import com.lsy.vehicle.security.dao.FleetGroupDao;
import com.lsy.vehicle.security.dao.UserDao;
import com.lsy.vehicle.security.domain.FleetGroup;
import com.lsy.vehicle.security.domain.User;
import com.lsy.vehicle.security.service.spi.SecurityServiceBean;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceBeanTest {
    
    @InjectMocks
    private SecurityServiceBean securityManager;
    
    @Mock
    private FleetGroupDao groupDao;
    
    @Mock
    private FleetDao fleetDao;
    
    @Mock
    private UserDao userDao;
    
    @Test
    public void testGetFleetGroup() {
        when(groupDao.findGroupByCompanyName(anyString())).thenReturn(null);
        when(userDao.findByUsername("username")).thenReturn(new User());
        
        securityManager.addUserToGroup("companyName", "username");
        
        verify(groupDao, times(1)).create(any(FleetGroup.class));
        verify(groupDao, times(1)).update(any(FleetGroup.class));
    }
    
    @Test
    public void testAddUserToGroup() {
        FleetGroup group = new FleetGroup();
        User user = new User();

        when(groupDao.findGroupByCompanyName(anyString())).thenReturn(group);
        when(userDao.findByUsername("username")).thenReturn(user);
        
        securityManager.addUserToGroup("companyName", "username");
        
        verify(groupDao, never()).create(any(FleetGroup.class));
        verify(groupDao, times(1)).update(group);
        
        assertTrue(group.getUsers().contains(user));
    }

}
