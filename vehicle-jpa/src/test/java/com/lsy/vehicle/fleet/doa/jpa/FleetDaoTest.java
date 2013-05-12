package com.lsy.vehicle.fleet.doa.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
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
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.fleet.dao.spi.jpa.DBFixtureFleets;
import com.lsy.vehicle.fleet.dao.spi.jpa.FleetJpaDao;
import com.lsy.vehicle.fleet.domain.EngineInfo;
import com.lsy.vehicle.fleet.domain.Fleet;
import com.lsy.vehicle.security.dao.spi.DBFixtureUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@Transactional
public class FleetDaoTest {

    @Autowired
    private FleetJpaDao dao;
    
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
    public void testPersitFleet() throws Exception {
        Fleet fleet = new Fleet();
        dao.create(fleet);
        assertNotNull(fleet.getId());
    }
    
    @Test
    public void testGetAllCompanyNames() throws Exception {
        List<String> names = dao.findAllCompanyNames();
        assertNotNull(names);
        assertFalse(names.isEmpty());
        assertEquals("crowdcode", names.get(0));
    }
    
    @Test
    public void testEngineReport() throws Exception {
        List<EngineInfo> report = dao.getEngineReport(DBFixtureFleets.COMPANY_NAME);
        assertNotNull(report);
        assertFalse(report.isEmpty());
        System.out.println(Arrays.toString(report.toArray()));
        assertEquals(EngineType.PETROL, report.get(0).getEngineType());
        assertEquals(1L, report.get(0).getQuantity());
        assertEquals(EngineType.DIESEL, report.get(1).getEngineType());
        assertEquals(2L, report.get(1).getQuantity());
    }
    
}
