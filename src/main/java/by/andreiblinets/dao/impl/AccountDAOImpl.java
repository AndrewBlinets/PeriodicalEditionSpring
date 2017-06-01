package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.AccountDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Account;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AccountDAOImpl extends BaseDAOImpl<Account> implements AccountDAO {

    private static final String PARAMETER_USER_LOGIN = "login";
    private static final String PARAMETER_USER_PASSWORD = "password";
    private static Logger logger = Logger.getLogger(AccountDAOImpl.class.getName());

    @Autowired
    public AccountDAOImpl()
    {
        super(Account.class, logger);
    }

    @Override
    public List<Account> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_ACCOUNT).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_ACCOUNT + e.getMessage());
        }
    }

    @Override
    public List<Integer> getAccountByLoginAndPassword(String login, String password) throws DaoException {
        try {
            Query query= getEntityManager().createNativeQuery(MyQuery.GET_USER_BY_LOGIN_AND_PASSWORD);
            query.setParameter(PARAMETER_USER_LOGIN, login);
            query.setParameter(PARAMETER_USER_PASSWORD, password);
            return query.getResultList();
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
            Query query= getEntityManager().createNativeQuery(MyQuery.CHEKING_LOGIN);
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
