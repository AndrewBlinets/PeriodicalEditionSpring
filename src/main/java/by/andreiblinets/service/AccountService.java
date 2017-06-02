package by.andreiblinets.service;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.exceptions.ServiceException;

public interface AccountService extends BaseService<Account> {
    User getUser(String login, String password) throws ServiceException;
    boolean chekingLogin(String login) throws ServiceException;
}
