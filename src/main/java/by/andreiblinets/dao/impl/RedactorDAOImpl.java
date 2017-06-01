package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.RedactorDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.Redactor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
public class RedactorDAOImpl  extends BaseDAOImpl<Redactor> implements RedactorDAO {

    private static final String PARAMETER_USER_ID = "id";
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

    @Override
    public List<Integer> getCamelCase(long id) throws DaoException {
        try {
            Query query= getEntityManager().createNativeQuery(MyQuery.GET_ID_CAMELCASE);
            query.setParameter(PARAMETER_USER_ID, id);
            return query.getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_GET_ACCOUNT_BY_LOGIN_AND_PASSWORD + e.getMessage());
        }
    }
}
