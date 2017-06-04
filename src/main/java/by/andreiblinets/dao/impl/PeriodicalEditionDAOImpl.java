package by.andreiblinets.dao.impl;

import by.andreiblinets.dao.PeriodicalEditionDAO;
import by.andreiblinets.constant.Error;
import by.andreiblinets.constant.MyQuery;
import by.andreiblinets.entity.PeriodicalEdition;
import by.andreiblinets.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PeriodicalEditionDAOImpl extends BaseDAOImpl<PeriodicalEdition> implements PeriodicalEditionDAO {

    private static final String PARAMETER_CAMELCASE_NAME = "name";
    private static Logger logger = Logger.getLogger(PeriodicalEditionDAOImpl.class.getName());

    public PeriodicalEditionDAOImpl() {
        super(PeriodicalEdition.class, logger);
    }

    @Override
    public List<PeriodicalEdition> readAll() throws DaoException {
        try {
            return getEntityManager().createQuery(MyQuery.GET_ALL_CAMEL_CASE).getResultList();
        }
        catch (HibernateException e)
        {
            logger.error(Error.ERROR_READ_CAMEL_CASE + e.getMessage());
            throw new DaoException(Error.ERROR_READ_CAMEL_CASE + e.getMessage());
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
            logger.error(Error.ERROR_CHEKING_LOGIN + e.getMessage());
            throw new DaoException(Error.ERROR_CHEKING_LOGIN + e.getMessage());
        }
    }
}
