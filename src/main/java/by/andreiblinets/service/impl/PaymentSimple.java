package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Payment;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentSimple implements BaseService<Payment> {

    private static Logger logger = Logger.getLogger(PaymentSimple.class.getName());

    @Autowired
    private BaseDAO<Payment> paymentBaseDAO;

    @Override
    public boolean create(Payment payment) throws ServiceException {
        try {
            paymentBaseDAO.create(payment);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(Payment payment) throws ServiceException {
        try {
            paymentBaseDAO.update(payment);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<Payment> readAll() throws ServiceException {
        try {
            return paymentBaseDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public Payment readById(Long id) throws ServiceException {
        try {
            return paymentBaseDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(Payment payment) throws ServiceException {
        try {
            paymentBaseDAO.delete(payment);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
