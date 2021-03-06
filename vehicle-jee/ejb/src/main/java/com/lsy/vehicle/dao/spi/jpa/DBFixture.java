package com.lsy.vehicle.dao.spi.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.domain.Vehicle;

@Startup
@Singleton
public class DBFixture {
	
	private static final Logger LOG = Logger.getLogger(DBFixture.class.getName());
	
    public static final String MANUFACTURER_VW = "VW";

    public static final String MANUFACTURER_BUGGATI = "Buggati";
    
    @PersistenceContext(unitName="vehicle-foundation")
    private EntityManager em;
    
    private List<Manufacturer> manufacturers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Engine> engines = new ArrayList<>();

    private Manufacturer currentManufacturer;

    private Vehicle currentVehicle;
    private Engine currentEngine;
    
    @PostConstruct
    public void postConstruct()
    {
       createDefaultDataInDatabase(); 
    }

    public void createDefaultDataInDatabase() {
    	LOG.info("Creating dummy data...");
        this.createManufacturer(MANUFACTURER_BUGGATI)
            .addVehicle()
            .setModelName("Veyron")
            .setNettoPrice(1200000.00)
            .addEngine(EngineType.PETROL)
            .setConstructionDate(new Date())
            .addVehicle()
            .setModelName("Veyron Diesel")
            .setNettoPrice(999000.00)
            .setConstructionDate(new Date())
            .addEngine(EngineType.DIESEL)
            .createManufacturer(MANUFACTURER_VW)
            .addVehicle()
            .setModelName("Trabbi")
            .setConstructionDate(new Date())
            .addEngine(EngineType.PETROL)
            .createManufacturer("AUDI")
            .addVehicle()
            .setModelName("A4")
            .addEngine(EngineType.DIESEL)
            .setConstructionDate(new Date())
            .persistAll();
    }

    private DBFixture setConstructionDate(Date date) {
        currentVehicle.setConstructionDate(date);
        return this;
    }

    public DBFixture persistAll() {
        persistAll(manufacturers);
        persistAll(engines);
        persistAll(vehicles);
        return this;
    }
        
    private DBFixture clear() {
        manufacturers.clear();
        vehicles.clear();
        engines.clear();
        currentEngine = null;
        currentVehicle = null;
        currentManufacturer = null;
        return null;
    }
    
    public DBFixture removeAll() {
        em.createQuery("DELETE FROM Fleet").executeUpdate();
        em.createQuery("DELETE FROM Vehicle").executeUpdate();
        em.createQuery("DELETE FROM Engine").executeUpdate();
        em.createQuery("DELETE FROM Manufacturer").executeUpdate();
        em.createQuery("DELETE FROM ApplicationLog").executeUpdate();
        clear();
        return this;
    }
    
    protected void persistAll(List<?> entities) {
        for (Object entity : entities) {
            System.out.println("--- persisting : "+entity);
            em.persist(entity);
        }
    }
    
    public Manufacturer build() {
        return currentManufacturer;
    }

    public DBFixture addEngine(EngineType engineType) {
        currentEngine = new Engine();
        engines.add(currentEngine);
        currentEngine.setType(engineType);
        currentVehicle.setEngine(currentEngine);
        currentEngine.setManufacturer(currentManufacturer);
        return this;
    }

    public DBFixture setModelName(String modelName) {
        currentVehicle.setModel(modelName);
        return this;
    }
    
    public DBFixture setNettoPrice(Double nettoPrice) {
        currentVehicle.setNettoPrice(nettoPrice);
        return this;
    }

    public DBFixture addVehicle() {
        currentVehicle = new Vehicle();
        vehicles.add(currentVehicle);
        currentManufacturer.addVehicle(currentVehicle);
        return this;
    }
    
    public DBFixture ownedByManufacturer() {
        currentManufacturer.getOwnedEngines().add(currentEngine);
        currentEngine.setManufacturer(currentManufacturer);
        return this;
    }

    public DBFixture createManufacturer(String name) {
        currentManufacturer = buildManufacturer(name);
        return this;
    }
    
    public Manufacturer buildManufacturer(String manufacturerName) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerName);
        manufacturers.add(manufacturer);
        return manufacturer;
    }

    public Vehicle buildVehicle(String model) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        return vehicle;
    }
    
    public void terminateAllActiveSessionInDB() {
        Query nativeQuery = em.createNativeQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    
    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public Engine firstEngine() {
        return engines.get(0);
    }

}
