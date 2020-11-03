package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.DataProcesingException;
import com.dev.cinema.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger log = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        log.info("Adding new role " + role);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
            log.info("New role added successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcesingException("Can't add new role " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        log.info("Try to get role by name " + roleName);
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery(
                    "FROM Role WHERE roleName = :roleName", Role.class)
                    .setParameter("roleName", Role.RoleName.valueOf(roleName));
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcesingException("Can't get role by name " + roleName, e);
        }
    }
}
