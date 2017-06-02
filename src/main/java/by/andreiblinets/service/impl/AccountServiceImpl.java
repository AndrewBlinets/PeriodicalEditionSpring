package by.andreiblinets.service.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.AccountService;
import by.andreiblinets.constant.ConstantsService;
import by.andreiblinets.exceptions.ServiceException;
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
            User user = null;
            List<Integer> list_Id = accountDAO.getAccountByLoginAndPassword(login, password);
            if(list_Id.size() != 0)
            {
                user = userBaseDAO.readById((long) list_Id.get(0));
            }
            return user;
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
