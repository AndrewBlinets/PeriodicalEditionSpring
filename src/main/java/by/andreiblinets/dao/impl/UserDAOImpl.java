package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements BaseDAO<User> {

    private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    public void create(User user) throws DaoException {
        try {
            entityManager.persist(user);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_USER + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_USER + e.getMessage());
        }
    }

    public void update(User user) throws DaoException {
        try {
            entityManager.merge(user);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE_USER + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE_USER + e.getMessage());
        }
    }

    public List<User> readAll() throws DaoException {
        try {
            return entityManager.createQuery(Query.GET_ALL_USERS).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_USER + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_USER + e.getMessage());
        }
    }

    public User readById(Long id) throws DaoException {
        try {
            return entityManager.find(User.class,id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_USER + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_USER + e.getMessage());
        }
    }

    public void delete(User user) throws DaoException {
        try {
            entityManager.remove(user);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_DELETE_USER + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_DELETE_USER + e.getMessage());
        }
    }
}
