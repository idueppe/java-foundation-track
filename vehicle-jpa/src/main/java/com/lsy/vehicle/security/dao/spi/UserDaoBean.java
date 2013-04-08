package com.lsy.vehicle.security.dao.spi;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lsy.vehicle.security.dao.UserDao;
import com.lsy.vehicle.security.domain.Role;
import com.lsy.vehicle.security.domain.User;

@Repository
public class UserDaoBean implements UserDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public User find(Long id) {
        return em.find(User.class,id);
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
    }

    @Override
    public List<User> findAllOfRole(Role role) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_ROLES, User.class);
        query.setParameter("role", role);
        return query.getResultList();
    }

}
