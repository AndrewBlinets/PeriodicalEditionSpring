package by.andreiblinets.dao;

import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.Account;

import java.util.List;

public interface AccountDAO extends BaseDAO<Account> {
    List<Account> getAccountByLoginAndPassword(String login, String password) throws DaoException;
    boolean chekingLogin(String login) throws DaoException;

    Account getAccountByLogin(String login);
}
