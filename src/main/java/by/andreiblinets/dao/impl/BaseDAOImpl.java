package by.andreiblinets.dao.impl;


import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseDAOImpl <T extends AbstractEntity> implements BaseDAO<T> {

    private static Logger logger;

    private Class aClass;

    public BaseDAOImpl(Class aClass, Logger aLogger){
        this.aClass = aClass;
        logger = aLogger;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(T t) throws DaoException {
        try {
            entityManager.persist(t);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
        }
    }

    @Override
    public void update(T t) throws DaoException {
        try {
            entityManager.merge(t);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
        }
    }

    @Override
    public List<T> readAll() throws DaoException {
       return null;
    }

    @Override
    public T readById(Long id) throws DaoException {
        try {
            return (T) entityManager.find(aClass, id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
        }
    }

    @Override
    public void delete(T t) throws DaoException {
        try {
            entityManager.remove(t);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_DELETE + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_DELETE + aClass.getName() + ErrorDAO.IN_DB + e.getMessage());
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
