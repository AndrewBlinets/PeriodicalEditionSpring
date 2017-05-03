package by.andreiblinets.service.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicalEditionSimpl implements BaseService<PeriodicalEdition> {

    private static Logger logger = Logger.getLogger(PeriodicalEditionSimpl.class.getName());

    @Autowired
    private BaseDAO<PeriodicalEdition> periodicalEditionBaseDAO;

    @Override
    public boolean create(PeriodicalEdition periodicalEdition) throws ServiceException {
        try {
            periodicalEditionBaseDAO.create(periodicalEdition);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(PeriodicalEdition periodicalEdition) throws ServiceException {
        try {
            periodicalEditionBaseDAO.update(periodicalEdition);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<PeriodicalEdition> readAll() throws ServiceException {
        try {
            return periodicalEditionBaseDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public PeriodicalEdition readById(Long id) throws ServiceException {
        try {
            return periodicalEditionBaseDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(PeriodicalEdition periodicalEdition) throws ServiceException {
        try {
            periodicalEditionBaseDAO.delete(periodicalEdition);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
