package by.andreiblinets.dao;

import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;

import java.util.List;

public interface AccountDAO extends BaseDAO<Account> {
    Account getAccountByLoginAndPassword(String login, String password) throws DaoException;
    boolean chekingLogin(String login) throws DaoException;
}
