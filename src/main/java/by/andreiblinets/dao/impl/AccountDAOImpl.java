package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private static Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    public void create(Account account) throws DaoException {
        try {
            entityManager.persist(account);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_ACCOUNT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_ACCOUNT + e.getMessage());
        }
    }

    public void update(Account account) throws DaoException {
        try {
            entityManager.merge(account);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE_ACCOUNT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE_ACCOUNT + e.getMessage());
        }
    }

    public List<Account> readAll() throws DaoException {
        try {
            return entityManager.createQuery(Query.GET_ALL_ACCOUNT).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
        }
    }

    public Account readById(Long id) throws DaoException {
        try {
            return entityManager.find(Account.class, id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
        }
    }

    public void delete(Account account) throws DaoException {
        try {
            entityManager.remove(account);
        }
        catch (HibernateException e)
        {
        logger.error(ErrorDAO.ERROR_DELETE_ACCOUNT + e.getMessage());
        throw new DaoException(ErrorDAO.ERROR_DELETE_ACCOUNT + e.getMessage());
        }
    }

    public Account getAccountByLoginAndPassword(String login, String password) throws DaoException {
        try {
            return (Account) entityManager
                    .createStoredProcedureQuery(Query.GET_USER_BY_LOGIN_AND_PASSWORD,login,password).getResultList();
        }
        catch (HibernateException e)
        {
        logger.error(ErrorDAO.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
        throw new DaoException(ErrorDAO.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
        }
    }

    public boolean chekingLogin(String login) throws DaoException {
        try {
            return entityManager.createStoredProcedureQuery(Query.CHEKING_LOGIN, login).getFirstResult() == 0;
        }
        catch (HibernateException e)
        {
        logger.error(ErrorDAO.ERROR_CHEKING_LOGIN + e.getMessage());
        throw new DaoException(ErrorDAO.ERROR_CHEKING_LOGIN + e.getMessage());
        }
    }
}
