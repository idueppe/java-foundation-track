package com.lsy.vehicle.fleet.dao.spi.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lsy.vehicle.domain.Vehicle;
import com.lsy.vehicle.fleet.domain.Fleet;

@Repository
public class DBFixtureFleets {
	
	private static final Logger LOG = Logger.getLogger(DBFixtureFleets.class.getName());

    @PersistenceUnit(unitName="vehicle-foundation")
    private EntityManagerFactory emf;
    
    private EntityManager em;
    
    private List<Fleet> fleets = new ArrayList<>();

    private Fleet currentFleet;
    
    public void createDefaultDataInDatabase() {
        em = emf.createEntityManager();
    	LOG.info("Creating dummy data...");
        this.createFleet("crowdcode")
            .addVehicleToFleet(0l)
            .persistAll();
    }

    public DBFixtureFleets persistAll() {
        beginTx();
        persistAll(fleets);
        commitTx();
        return this;
    }
    
    private DBFixtureFleets clear() {
        fleets.clear();
        currentFleet = null;
        return null;
    }
    
    protected void beginTx() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();
    }
    
    protected void commitTx() {
        em.getTransaction().commit();
        em.close();
        em = null;
    }
    
    public DBFixtureFleets unlinkFleets() {
        for (Fleet fleet: fleets) {
            fleet.getVehicles().clear();
        }
        return this;
    }

    public DBFixtureFleets removeAll() {
        beginTx();
        unlinkFleets();
        em.createQuery("DELETE FROM Fleet").executeUpdate();
        fleets.clear();
        clear();
        commitTx();
        return this;
    }
    
    private void persistAll(List<?> entities) {
        for (Object entity : entities) {
            System.out.println("--- persisting : "+entity);
            em.persist(entity);
        }
    }
    
    public DBFixtureFleets createFleet(String companyName) {
        currentFleet = new Fleet();
        currentFleet.setCompanyName(companyName);
        fleets.add(currentFleet);
        return this;
    }
    
    public DBFixtureFleets addVehicleToFleet(Long vehicleId) {
        Vehicle vehicle = em.find(Vehicle.class, vehicleId);
        currentFleet.getVehicles().add(vehicle);
        return this;
    }

    public void terminateAllActiveSessionInDB() {
        Query nativeQuery = em.createNativeQuery("SELECT pg_terminate_backend(procpid) FROM pg_stat_activity WHERE datname = 'vehicle-tmp'");
        nativeQuery.executeUpdate();
    }
    
}