package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.PeriodicalEdition;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PeriodicalEditionDAOImpl implements BaseDAO<PeriodicalEdition> {

    private static Logger logger = Logger.getLogger(PeriodicalEditionDAOImpl.class.getName());

    @Autowired
    private EntityManager entityManager;

    @Override
    public void create(PeriodicalEdition periodicalEdition) throws DaoException {
        try {
            entityManager.persist(periodicalEdition);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public void update(PeriodicalEdition periodicalEdition) throws DaoException {
        try {
            entityManager.merge(periodicalEdition);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public List<PeriodicalEdition> readAll() throws DaoException {
        try {
            return entityManager.createQuery(Query.GET_ALL_PERIODICAL_EDITION).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public PeriodicalEdition readById(Long id) throws DaoException {
        try {
            return entityManager.find(PeriodicalEdition.class, id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public void delete(PeriodicalEdition periodicalEdition) throws DaoException {
        try {
            entityManager.remove(periodicalEdition);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_DELETE_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_DELETE_PERIODICALEDITION + e.getMessage());
        }
    }
}
