package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcesingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static final Logger log = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        log.info("Adding new order " + order);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            log.info("New order added successfully");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcesingException("Can't add order "
                    + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        log.info("Try to get all orders by user " + user);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("FROM Order o "
                    + "JOIN FETCH o.tickets "
                    + "WHERE o.user =: user", Order.class);
            query.setParameter("user", user);
            return query.getResultList().stream()
                    .distinct()
                    .collect(Collectors.toList());
        }
    }
}
