package com.lsy.vehicle.security.dao.spi;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<User> findAllCustomersNotMemberOfCompany(String companyName) {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_NO_CUSTOMER_MEMBER_BY_COMPANY_NAME, User.class);
        query.setParameter("companyName", companyName);
        return query.getResultList();
    }

    @Override
    public List<User> findByFilter(String username, String email, String firstname,
                    String surename, Role role) {
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Metamodel metaModel = em.getMetamodel();
        
        EntityType<User> user_ = metaModel.entity(User.class);
        Root<User> user = criteriaQuery.from(user_);
        
        List<Predicate> predicates = buildPredicates(username, email, firstname, surename, role, builder, user);
        
        
        Predicate conjunction = conjunction(builder, predicates);
        if (conjunction != null) {
            criteriaQuery.where(conjunction);
        }
        
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        
        return query.getResultList();
    }

    private List<Predicate> buildPredicates(String username, String email, String firstname, String surename,
                    Role role, CriteriaBuilder builder, Root<User> user) {
        List<Predicate> predicates = new LinkedList<>();
        if (StringUtils.isNotBlank(username)) {
            Expression<String> expression = user.get("username");
            predicates.add(builder.like(expression, username));
        }
        if (StringUtils.isNotBlank(email)) {
            Expression<String> expression = user.get("email");
            predicates.add(builder.like(expression, email));
        }
        if (StringUtils.isNotBlank(firstname)) {
            Expression<String> expression = user.get("firstname");
            predicates.add(builder.like(expression, firstname));
        }
        if (StringUtils.isNotBlank(surename)) {
            Expression<String> expression = user.get("surename");
            predicates.add(builder.like(expression, surename));
        }
        if (role != null) {
            Expression<Role> expression = user.get("role");
            predicates.add(builder.equal(expression, role));
        }
        return predicates;
    }

    private Predicate conjunction(CriteriaBuilder builder, List<Predicate> predicates) {
        Predicate current = null;
        for (Predicate item: predicates) {
            if (current == null) {
                current = item;
            } else {
                current = builder.and(current, item);
            }
        }
        return current;
    }

    
}
