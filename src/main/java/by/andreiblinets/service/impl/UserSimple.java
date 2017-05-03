package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSimple implements BaseService<User> {

    private static Logger logger = Logger.getLogger(UserSimple.class.getName());

    @Autowired
    private BaseDAO<User> userBaseDAO;

    @Override
    public boolean create(User user) throws ServiceException {
        try {
            userBaseDAO.create(user);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        try {
            userBaseDAO.update(user);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<User> readAll() throws ServiceException {
        try {
            return userBaseDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public User readById(Long id) throws ServiceException {
        try {
            return userBaseDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(User user) throws ServiceException {
        try {
            userBaseDAO.delete(user);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
