package by.andreiblinets.service.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.service.BaseService;
import by.andreiblinets.service.constant.ConstantsService;
import by.andreiblinets.service.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    private static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BaseDAO<User> userBaseDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        super(accountDAO,logger);
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
