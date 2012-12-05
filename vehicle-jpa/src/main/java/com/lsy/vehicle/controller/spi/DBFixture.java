package com.lsy.vehicle.controller.spi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.lsy.vehicle.domain.Engine;
import com.lsy.vehicle.domain.EngineType;
import com.lsy.vehicle.domain.Manufacturer;
import com.lsy.vehicle.domain.Vehicle;

@Service
public class DBFixture {

    public static final String MANUFACTURER_VW = "VW";

	public static final String MANUFACTURER_BUGGATI = "Buggati";

	@PersistenceUnit(unitName="vehicle-foundation")
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private List<Manufacturer> manufacturers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Engine> engines = new ArrayList<>();

    private Manufacturer currentManufacturer;

    private Vehicle currentVehicle;

    private Engine currentEngine;

    @PostConstruct
    public void createDefaultDataInDatabase() {
        this.createManufacturer(MANUFACTURER_BUGGATI)
            .addVehicle()
            .setModelName("Veyron")
            .setNettoPrice(1_200_000.00)
            .addEngine(EngineType.PETROL)
            .ownedByManufacturer()
            .addVehicle()
            .setModelName("Veyron Diesel")
            .setNettoPrice(999_000.00)
            .addEngine(EngineType.DIESEL)
            .ownedByManufacturer()
            .createManufacturer(MANUFACTURER_VW)
            .addVehicle()
            .setModelName("Trabbi")
            .addEngine(EngineType.PETROL)
            .persistAll();
    }
    
    
    public DBFixture persistAll() {
        beginTx();
        persistAll(manufacturers);
        persistAll(engines);
        persistAll(vehicles);
        commitTx();
        return this;
    }
    
    private void beginTx() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    private void commitTx() {
        em.getTransaction().commit();
        em.close();
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
        beginTx();
        em.createQuery("DELETE FROM Vehicle").executeUpdate();
        em.createQuery("DELETE FROM Engine").executeUpdate();
        em.createQuery("DELETE FROM Manufacturer").executeUpdate();
        em.createQuery("DELETE FROM ApplicationLog").executeUpdate();
        commitTx();
        clear();
        return this;
    }
    
    private void persistAll(List<?> entities) {
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
        Query nativeQuery = em.createNamedQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    
    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }


	public Engine firstEngine() {
		return engines.get(0);
	}

}
