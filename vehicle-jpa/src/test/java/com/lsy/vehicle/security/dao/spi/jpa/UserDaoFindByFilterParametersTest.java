package com.lsy.vehicle.security.dao.spi.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lsy.vehicle.controller.spi.DBFixture;
import com.lsy.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import com.lsy.vehicle.security.dao.spi.DBFixtureUser;
import com.lsy.vehicle.security.dao.spi.UserDaoBean;
import com.lsy.vehicle.security.domain.Role;
import com.lsy.vehicle.security.domain.User;
import com.lsy.vehicle.security.filter.UserColumn;
import com.lsy.vehicle.security.filter.UserFilterParameters;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class UserDaoFindByFilterParametersTest {

    @Autowired
    private UserDaoBean dao;
    
    @Autowired
    private DBFixture dbFixture;
    
    @Autowired
    private DBFixtureFleets dbFixtureFleets;
    
    @Autowired
    private DBFixtureUser dbFixtureUser;
    
    @Before
    public void setUp() {
        dbFixture.createDefaultDataInDatabase();
        dbFixtureFleets.createDefaultDataInDatabase();
        dbFixtureUser.createDefaultDataInDatabase();
    }
    
    @After
    public void tearDown() {
        dbFixtureUser.removeAll();
        dbFixtureFleets.removeAll();
        dbFixture.removeAll();
    }
    
    @Test
    public void testFindAdmin() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.USERNAME).setFilter("admin");
        params.getColumn(UserColumn.EMAIL).setFilter("admin@junit");
        params.getColumn(UserColumn.ROLE).setFilter(Role.ADMIN.toString());
        List<User> users = dao.findByFilter(params);
        
        assertNotNull("Users must not be NULL.", users);
        assertFalse("Users should not be empty.", users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("admin", users.get(0).getUsername());
        assertEquals("admin@junit", users.get(0).getEmail());
        assertEquals(Role.ADMIN, users.get(0).getRole());
    }

    @Test
    public void testFindAllByEmail() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.EMAIL).setFilter("@junit");
        List<User> users = dao.findByFilter(params);

        assertEquals(3, users.size());
    }

    @Test
    public void testFindAllByEmailSearchingAdd() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.EMAIL).setFilter("@");
        List<User> users = dao.findByFilter(params);

        assertEquals(3, users.size());
    }

    @Test
    public void testFindAll() {
        List<User> users = dao.findByFilter(new UserFilterParameters());
        assertEquals(3, users.size());
    }
    
    @Test
    public void testFindAllWithOrder() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.USERNAME).toggleOrder(); // ASCENDING
        params.getColumn(UserColumn.USERNAME).toggleOrder(); // DESCENDING
        List<User> users = dao.findByFilter(params);
        assertEquals(3, users.size());
        assertEquals("customer", users.get(0).getUsername());
    }
    
    @Test
    public void testFindCustomers() {
        UserFilterParameters params = new UserFilterParameters();
        params.getColumn(UserColumn.ROLE).setFilter(Role.CUSTOMER.toString());
        
        List<User> users = dao.findByFilter(params);
        assertEquals(1, users.size());
        assertEquals(Role.CUSTOMER, users.get(0).getRole());
    }
                
       
    
}
