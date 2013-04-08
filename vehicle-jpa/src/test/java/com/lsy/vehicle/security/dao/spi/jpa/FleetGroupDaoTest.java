package com.lsy.vehicle.security.dao.spi.jpa;

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
import org.springframework.transaction.annotation.Transactional;

import com.lsy.vehicle.controller.spi.DBFixture;
import com.lsy.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import com.lsy.vehicle.security.dao.spi.DBFixtureUser;
import com.lsy.vehicle.security.dao.spi.FleetGroupDaoBean;
import com.lsy.vehicle.security.domain.FleetGroup;
import com.lsy.vehicle.security.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@Transactional
public class FleetGroupDaoTest {

    @Autowired
    private FleetGroupDaoBean dao;
    
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
    public void testFindGroupByCompanyName() {
        FleetGroup group = dao.findGroupByCompanyName("crowdcode");
        assertNotNull("Dao should find the group by companyName crowdcode",group);
        List<User> users = group.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }
    
}
