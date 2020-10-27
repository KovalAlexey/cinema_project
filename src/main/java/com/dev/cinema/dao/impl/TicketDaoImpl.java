package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.exceptions.DataProcesingException;
import com.dev.cinema.model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private static final Logger log = Logger.getLogger(TicketDaoImpl.class);
    private final SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        log.info("Adding new ticket " + ticket);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            log.info("New ticket added successfully");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcesingException("Can't add ticket " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Ticket getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            throw new DataProcesingException("Can't get ticket with id " + id, e);
        }
    }
}
