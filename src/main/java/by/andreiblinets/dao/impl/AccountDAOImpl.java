package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private static final String PARAMETER_USER_LOGIN = "login";
    private static Logger logger = Logger.getLogger(AccountDAOImpl.class.getName());

    @PersistenceContext
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

    @Override
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

    @Override
    public List<Account> readAll() throws DaoException {
        try {
            return entityManager.createQuery(MyQuery.GET_ALL_ACCOUNT).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
        }
    }

    @Override
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

    @Override
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

    @Override
    public Account getAccountByLoginAndPassword(String login, String password) throws DaoException {
        try {
            return (Account) entityManager
                    .createStoredProcedureQuery(MyQuery.GET_USER_BY_LOGIN_AND_PASSWORD,login,password).getResultList();
        }
        catch (HibernateException e)
        {
        logger.error(ErrorDAO.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
        throw new DaoException(ErrorDAO.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
        }
    }

    @Override
    public boolean chekingLogin(String login) throws DaoException {
        try {
            Query query= entityManager.createNativeQuery(MyQuery.CHEKING_LOGIN);
            query.setParameter(PARAMETER_USER_LOGIN, login);

            if(query.getResultList().size() != 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (HibernateException e)
        {
        logger.error(ErrorDAO.ERROR_CHEKING_LOGIN + e.getMessage());
        throw new DaoException(ErrorDAO.ERROR_CHEKING_LOGIN + e.getMessage());
        }
    }
}
