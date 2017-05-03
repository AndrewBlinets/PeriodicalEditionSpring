package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.impl.PaymentDAOImpl;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.service.BaseService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionSimple implements BaseService<Subscription> {

    private static Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Autowired
    private BaseDAO<Subscription> subscriptionBaseDAO;

    @Override
    public boolean create(Subscription subscription) {
        try {
            subscriptionBaseDAO.create(subscription);
            return true;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Subscription subscription) {
        try {
            subscriptionBaseDAO.update(subscription);
            return true;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Subscription> readAll() {
        try {
            List<Subscription> subscriptions = subscriptionBaseDAO.readAll();
            return subscriptions;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return new ArrayList<Subscription>();
        }

    }

    @Override
    public Subscription readById(Long id) {
        Subscription subscription = null;
        try {
            subscription = subscriptionBaseDAO.readById(id);
            return subscription;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return subscription;
        }
    }

    @Override
    public boolean delete(Subscription subscription) {
        try{
            subscriptionBaseDAO.delete(subscription);
            return true;
        }
        catch (HibernateException e)
        {
            logger.error(e.getMessage());
            return false;
        }
    }
}
