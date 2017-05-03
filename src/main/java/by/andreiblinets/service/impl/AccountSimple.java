package by.andreiblinets.service.impl;

import by.andreiblinets.dao.AccountDAO;
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

    private static Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public boolean create(Account account) throws ServiceException {
        try {
            accountDAO.create(account);
            logger.error(ConstantsService.TRANSACTION_SUCCESSFULLY);
        } catch (DaoException e) {
            logger.error( ConstantsService.TRANSACTION_FAIL + e.getMessage());
            throw new ServiceException(ConstantsService.TRANSACTION_FAIL + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Account account) {
        accountDAO.update(account);
        return true;
    }

    @Override
    public List<Account> readAll() {
        return accountDAO.readAll();
    }

    @Override
    public Account readById(Long id) {
        return accountDAO.readById(id);
    }

    @Override
    public boolean delete(Account account) {
        accountDAO.delete(account);
        return true;
    }

    @Override
    public User get(String login, String password) {
        return accountDAO.getAccountByLoginAndPassword(login, password);
    }

    @Override
    public boolean getLogin(Account account) {
        return accountDAO.chekingLogin(account);
    }
}
