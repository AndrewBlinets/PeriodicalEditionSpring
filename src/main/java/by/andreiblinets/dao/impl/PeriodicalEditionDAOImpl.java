package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.BaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.Query;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.CamelCase;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PeriodicalEditionDAOImpl implements BaseDAO<CamelCase> {

    private static Logger logger = Logger.getLogger(PeriodicalEditionDAOImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(CamelCase camelCase) throws DaoException {
        try {
            entityManager.persist(camelCase);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_ADD_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_ADD_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public void update(CamelCase camelCase) throws DaoException {
        try {
            entityManager.merge(camelCase);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_UPDATE_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_UPDATE_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public List<CamelCase> readAll() throws DaoException {
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
    public CamelCase readById(Long id) throws DaoException {
        try {
            return entityManager.find(CamelCase.class, id);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_PERIODICALEDITION + e.getMessage());
        }
    }

    @Override
    public void delete(CamelCase camelCase) throws DaoException {
        try {
            entityManager.remove(camelCase);
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_DELETE_PERIODICALEDITION + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_DELETE_PERIODICALEDITION + e.getMessage());
        }
    }
}
