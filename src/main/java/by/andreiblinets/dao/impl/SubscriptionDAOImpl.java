package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Subscription;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubscriptionDAOImpl implements BaseDAO<Subscription> {

    private static Logger logger = Logger.getLogger(SubscriptionDAOImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Subscription subscription) throws DaoException {
        try {
            entityManager.persist(subscription);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_SUBSCRIPTION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_SUBSCRIPTION + e.getMessage());
        }
    }

    @Override
    public void update(Subscription subscription) throws DaoException {
        try {
            entityManager.merge(subscription);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE_SUBSCRIPTION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE_SUBSCRIPTION + e.getMessage());
        }
    }

    @Override
    public List<Subscription> readAll() throws DaoException {
        try {
            return entityManager.createQuery(Query.GET_ALL_SUBSCRIPTION).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_SUBSCRIPTION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_SUBSCRIPTION + e.getMessage());
        }
    }

    @Override
    public Subscription readById(Long id) throws DaoException {
        try {
            return entityManager.find(Subscription.class,id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_SUBSCRIPTION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_SUBSCRIPTION + e.getMessage());
        }
    }

    @Override
    public void delete(Subscription subscription) throws DaoException {
        try {
            entityManager.remove(subscription);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_DELETE_SUBSCRIPTION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_DELETE_SUBSCRIPTION + e.getMessage());
        }
    }
}
