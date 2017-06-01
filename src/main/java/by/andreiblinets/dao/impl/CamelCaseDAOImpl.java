package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.CamelCaseDAO;
import by.andreiblinets.dao.constant.ErrorDAO;
import by.andreiblinets.dao.constant.MyQuery;
import by.andreiblinets.dao.exceptions.DaoException;
import by.andreiblinets.entity.CamelCase;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CamelCaseDAOImpl extends BaseDAOImpl<CamelCase> implements CamelCaseDAO {

    private static final String PARAMETER_CAMELCASE_NAME = "name";
    private static Logger logger = Logger.getLogger(CamelCaseDAOImpl.class.getName());

    public CamelCaseDAOImpl() {
        super(CamelCase.class, logger);
    }

    @Override
    public List<CamelCase> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_CAMEL_CASE).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(ErrorDAO.ERROR_READ_CAMEL_CASE + e.getMessage());
            throw new DaoException(ErrorDAO.ERROR_READ_CAMEL_CASE + e.getMessage());
        }
    }

    @Override
    public boolean chekingNameCamelCase(String name) throws DaoException {
        try {
            Query query= getEntityManager().createNativeQuery(MyQuery.CHEKING_CAMELCASE);
            query.setParameter(PARAMETER_CAMELCASE_NAME, name);

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
