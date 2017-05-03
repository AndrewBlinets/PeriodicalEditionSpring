package by.andreiblinets.service;

import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.service.exceptions.ServiceException;

public interface AccountService extends BaseService<Account> {
    User getUser(String login, String password) throws DaoException, ServiceException;
    boolean chekingLogin(String login) throws ServiceException;
}
