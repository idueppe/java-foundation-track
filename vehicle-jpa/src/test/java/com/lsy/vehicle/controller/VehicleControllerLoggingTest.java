package com.lsy.vehicle.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lsy.vehicle.controller.VehicleController;
import com.lsy.vehicle.dao.ApplicationLogDao;
import com.lsy.vehicle.doa.jpa.DBFixture;
import com.lsy.vehicle.dto.VehicleDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class VehicleControllerLoggingTest {

    @Autowired
    private VehicleController vehicleController;

    @Autowired
    private ApplicationLogDao dao;

    @Autowired
    private DBFixture dbFixture;

    @Before
    public void setUp() {
        dbFixture.createDefaultDataInDatabase();
    }

    @After
    public void tearDown() {
        dbFixture.removeAll();
    }

    @Test
    public void testConfiguration() {
        assertNotNull(vehicleController);
    }

    @Test
    public void testLogExistAfterException() {
        try {
            vehicleController.saveOrUpdateVehicle(buildVehicleErrorData());
        } catch (NullPointerException npe) {
            // expected
        }
        assertEquals("No log entry found.", 1, dao.findAll().size());
    }

    private VehicleDto buildVehicleErrorData() {
        VehicleDto vehicle = new VehicleDto();
        vehicle.setManufacturerName("Buggati");
        vehicle.setConstructionDate(new Date());
        vehicle.setModelName("VEYRON UNIT TEST");
        return vehicle;
    }

}
