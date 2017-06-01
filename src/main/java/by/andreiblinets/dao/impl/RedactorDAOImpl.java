package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.RedactorDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Redactor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RedactorDAOImpl  extends BaseDAOImpl<Redactor> implements RedactorDAO {

    private static Logger logger = Logger.getLogger(RedactorDAOImpl.class.getName());

    public RedactorDAOImpl() {
        super(Redactor.class, logger);
    }

    @Override
    public List<Redactor> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_REDACTOR).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_REDACTOR + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_REDACTOR + e.getMessage());
        }
    }
}
