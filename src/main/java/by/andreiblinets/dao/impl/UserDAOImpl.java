package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.UserDAO;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.MyQuery;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

    private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    public UserDAOImpl() {
        super(User.class, logger);
    }

    @Override
    public List<User> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_USERS).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_USER + e.getMessage());
            throw new DaoException(Error.ERROR_READ_USER + e.getMessage());
        }
    }

    @Override
    public List<User> readReader() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_USERS_READER).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_USER + e.getMessage());
            throw new DaoException(Error.ERROR_READ_USER + e.getMessage());
        }
    }
}
