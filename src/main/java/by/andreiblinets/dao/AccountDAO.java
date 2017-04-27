package by.andreiblinets.dao;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;

import java.util.List;

public interface AccountDAO {

    void create(Account account);
    void update(Account account);
    List<Account> readAll();
    Account readById(Long id);
    void delete(Account account);
    User get(String login, String password);
    boolean getLogin(Account account);

}
