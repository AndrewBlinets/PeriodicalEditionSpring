package by.andreiblinets.service;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import by.andreiblinets.exceptions.ServiceException;

import java.util.List;

public interface UserService extends BaseService<User> {
    List<User> readReader() throws ServiceException;
    User getUser(String login);

    Account getAccount(String login);
}
