package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.AbstractEntity;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class BaseServiceImpl<T extends AbstractEntity> implements BaseService<T> {

    private static Logger logger;

    private BaseDAO<T> baseDAO;

    public BaseServiceImpl(BaseDAO<T> dao, Logger aLogger) {
        this.baseDAO = dao;
        logger = aLogger;
    }

    @Override
    public boolean create(T t) throws ServiceException {
        try {
            baseDAO.create(t);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(T t) throws ServiceException {
        try {
            baseDAO.update(t);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<T> readAll() throws ServiceException {
        try {
            return baseDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public T readById(Long id) throws ServiceException {
        try {
            return baseDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            baseDAO.delete(id);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
