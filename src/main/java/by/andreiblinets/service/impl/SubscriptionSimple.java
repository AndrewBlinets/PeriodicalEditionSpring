package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.dao.impl.PaymentDAOImpl;
import by.andreiblinets.entity.Subscription;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionSimple implements BaseService<Subscription> {

    private static Logger logger = Logger.getLogger(SubscriptionSimple.class.getName());

    @Autowired
    private BaseDAO<Subscription> subscriptionBaseDAO;

    @Override
    public boolean create(Subscription subscription) throws ServiceException {
        try {
            subscriptionBaseDAO.create(subscription);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(Subscription subscription) throws ServiceException {
        try {
            subscriptionBaseDAO.update(subscription);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<Subscription> readAll() throws ServiceException {
        try {
            return subscriptionBaseDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public Subscription readById(Long id) throws ServiceException {
        try {
            return subscriptionBaseDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(Subscription subscription) throws ServiceException {
        try {
            subscriptionBaseDAO.delete(subscription);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
