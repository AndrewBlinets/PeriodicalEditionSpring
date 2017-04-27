package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.entity.Account;
import by.andreiblinets.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private EntityManager entityManager;

    public void create(Account account) {
        entityManager.persist(account);
    }

    public void update(Account account) {
        entityManager.merge(account);
    }

    public List<Account> readAll() {
        return entityManager.createQuery(Query.GET_ALL_ACCOUNT).getResultList();
    }

    public Account readById(Long id) {
        return entityManager.find(Account.class, id);
    }

    public void delete(Account account) {
        entityManager.remove(account);
    }

    public User get(String login, String password) {
        return null;
    }

    public boolean getLogin(Account account) {
        //List<Account> accounts = entityManager.find(Account.class)
        return false;
    }
}
