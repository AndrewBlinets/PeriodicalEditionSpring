package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.constant.Error;
import by.andreiblinets.exceptions.DaoException;
import by.andreiblinets.entity.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
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
            logger.error(Error.ERROR_ADD + aClass.getName() + Error.IN_DB + e.getMessage());
            throw new DaoException(Error.ERROR_ADD + aClass.getName() + Error.IN_DB + e.getMessage());
        }
    }

    @Override
    public void update(T t) throws DaoException {
        try {
            entityManager.merge(t);
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_UPDATE + aClass.getName() + Error.IN_DB + e.getMessage());
            throw new DaoException(Error.ERROR_UPDATE + aClass.getName() + Error.IN_DB + e.getMessage());
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
            logger.error(Error.ERROR_READ + aClass.getName() + Error.IN_DB + e.getMessage());
            throw new DaoException(Error.ERROR_READ + aClass.getName() + Error.IN_DB + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try {
            entityManager.remove(entityManager.find(aClass,id));
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_DELETE + aClass.getName() + Error.IN_DB + e.getMessage());
            throw new DaoException(Error.ERROR_DELETE + aClass.getName() + Error.IN_DB + e.getMessage());
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
