package com.lsy.vehicle.dao.spi.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lsy.vehicle.dao.FleetDao;
import com.lsy.vehicle.domain.Fleet;


@Repository
public class FleetJpaDao implements FleetDao {

    @PersistenceContext(unitName = "vehicle-foundation")
    private EntityManager em;

    @Override
    public List<Fleet> findAll() {
        TypedQuery<Fleet> query = em.createNamedQuery(Fleet.FIND_ALL, Fleet.class);
        return query.getResultList();
    }

    @Override
    public Fleet find(Long id) {
        return em.find(Fleet.class, id);
    }

    @Override
    public void create(Fleet entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Fleet entity) {
        em.remove(entity);
    }

    @Override
    public Fleet update(Fleet entity) {
        return em.merge(entity);
    }

    @Override
    public Fleet findByCompanyName(String companyName) {
        TypedQuery<Fleet> query = em.createNamedQuery(Fleet.FIND_BY_COMPANY_NAME, Fleet.class);
        query.setParameter("companyName", companyName);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<String> findAllCompanyNames() {
        TypedQuery<String> query = em.createNamedQuery(Fleet.FIND_ALL_COMPANY_NAMES, String.class);
        return query.getResultList();
    }

}
