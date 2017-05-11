package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.CamelCase;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicalEditionSimpl implements BaseService<CamelCase> {

    private static Logger logger = Logger.getLogger(PeriodicalEditionSimpl.class.getName());

    @Autowired
    private BaseDAO<CamelCase> periodicalEditionBaseDAO;

    @Override
    public boolean create(CamelCase camelCase) throws ServiceException {
        try {
            periodicalEditionBaseDAO.create(camelCase);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(CamelCase camelCase) throws ServiceException {
        try {
            periodicalEditionBaseDAO.update(camelCase);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<CamelCase> readAll() throws ServiceException {
        try {
            return periodicalEditionBaseDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public CamelCase readById(Long id) throws ServiceException {
        try {
            return periodicalEditionBaseDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(CamelCase camelCase) throws ServiceException {
        try {
            periodicalEditionBaseDAO.delete(camelCase);
            logger.info(ConstantsService.TRANSACTION_SUCCESS);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
