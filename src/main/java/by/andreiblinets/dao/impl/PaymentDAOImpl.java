package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Payment;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PaymentDAOImpl implements BaseDAO<Payment> {

    private static Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    public void create(Payment payment) throws DaoException {
        try {
            entityManager.persist(payment);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_PAYMENT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_PAYMENT + e.getMessage());
        }
    }

    @Override
    public void update(Payment payment) throws DaoException {
        try {
            entityManager.merge(payment);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE_PAYMENT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE_PAYMENT + e.getMessage());
        }
    }

    @Override
    public List<Payment> readAll() throws DaoException {
        try {
            return entityManager.createQuery(Query.GET_ALL_PAYMENT).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_PAYMENT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_PAYMENT + e.getMessage());
        }
    }

    @Override
    public Payment readById(Long id) throws  DaoException {
        try {
            return entityManager.find(Payment.class, id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_PAYMENT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_PAYMENT + e.getMessage());
        }
    }

    @Override
    public void delete(Payment payment) throws DaoException {
        try {
            entityManager.remove(payment);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_DELETE_PAYMENT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_DELETE_PAYMENT + e.getMessage());
        }
    }
}
