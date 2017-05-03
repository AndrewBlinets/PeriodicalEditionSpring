package by.andreiblinets.service;

import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;

public interface AccountService extends BaseService<Account> {
    User get(String login, String password);
    boolean getLogin(Account account);
}
