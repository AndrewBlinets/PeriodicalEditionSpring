package by.andreiblinets.service.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.dao.impl.PaymentDAOImpl;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountSimple implements AccountService {

    private static Logger logger = Logger.getLogger(AccountSimple.class.getName());

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BaseDAO<User> userBaseDAO;

    @Override
    public boolean create(Account account) throws ServiceException {
        try {
            accountDAO.create(account);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
    }

    @Override
    public boolean update(Account account) throws ServiceException {
        try {
            accountDAO.update(account);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public List<Account> readAll() throws ServiceException {
        try {
            return accountDAO.readAll();
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public Account readById(Long id) throws ServiceException {
        try {
            return accountDAO.readById(id);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean delete(Account account) throws ServiceException {
        try {
            accountDAO.delete(account);
            logger.info(ConstantsService.TRANSACTION_SUCCESSFULLY);
            return true;
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public User getUser(String login, String password) throws ServiceException {
        try {
            return userBaseDAO.readById(accountDAO.getAccountByLoginAndPassword(login, password).getId());
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }

    @Override
    public boolean chekingLogin(String login) throws ServiceException {
        try {
            return accountDAO.chekingLogin(login);
        } catch (DaoException e) {
            logger.error(ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL);
        }
    }
}
